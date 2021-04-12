package mk.ukim.finki.emt.bookstore.service;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.emt.bookstore.dto.*;
import mk.ukim.finki.emt.bookstore.model.Author;
import mk.ukim.finki.emt.bookstore.model.Book;
import mk.ukim.finki.emt.bookstore.repository.impl.BookRepository;
import mk.ukim.finki.emt.bookstore.utils.BookMapper;
import mk.ukim.finki.emt.bookstore.utils.FieldsCannotBeNullOrEmptyException;
import mk.ukim.finki.emt.bookstore.utils.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static mk.ukim.finki.emt.bookstore.service.AuthorService.validateUpsertAuthor;
import static mk.ukim.finki.emt.bookstore.utils.ResponseMapper.*;
import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;


@Service
@RequiredArgsConstructor
public class BookService {

    private final static Integer PER_PAGE = 5;
    private final static Logger log = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    public BookResponseDto getAllBooksInPage(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, PER_PAGE);

        Page<Book> bookPage = bookRepository.findAll(pageRequest);

        List<BookDto> books = bookPage
            .get()
            .map(BookMapper::toDto)
            .collect(toList());

        return BookResponseDto
            .builder()
            .totalPages(bookPage.getTotalPages())
            .books(books)
            .build();
    }

    public ResponseDto addBook(UpsertBookDto upsertBookDto) {

        List<FieldErrorDto> fieldErrors = validateUpsertBook(upsertBookDto);

        if (!fieldErrors.isEmpty()) {
            return withFieldErrors(fieldErrors);
        }

        try {
            Long authorId = upsertBookDto.getAuthorId();
            UpsertAuthorDto upsertAuthorDto = upsertBookDto.getUpsertAuthorDto();

            Author author = null;
            if (authorId != null) {
                author = authorService.findAuthorOrThrowException(authorId);
            } else if (upsertAuthorDto != null) {
                author = authorService.saveAuthor(upsertAuthorDto);
            }

            Book book = BookMapper.toModel(upsertBookDto, author);

            bookRepository.save(book);
        } catch (NotFoundException e) {
            return errorResponse(e.getMessage());
        } catch (FieldsCannotBeNullOrEmptyException e) {
            List<FieldErrorDto> fieldErrorsFromException = e.getFieldErrors();

            return withFieldErrors(fieldErrorsFromException);
        } catch (Exception e) {
            log.error(e.getMessage());

            return errorResponse("Something went wrong while saving the book. Please try again later");
        }

        return successResponse();
    }

    public ResponseDto editBook(Long bookId, UpsertBookDto upsertBookDto) {
        try {
            Book book = findBookOrThrowException(bookId);

            Long authorId = upsertBookDto.getAuthorId();
            Author author = authorService.findAuthorOrThrowException(authorId);

            // Fill existing model with upsert data
            BookMapper.fillModel(book, upsertBookDto, author);

            bookRepository.save(book);
        } catch (NotFoundException e) {
            return errorResponse(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());

            return errorResponse("Something went wrong while saving the book. Please try again later");
        }

        return successResponse();
    }

    public ResponseDto deleteBook(Long bookId) {
        try {
            Book book = findBookOrThrowException(bookId);

            bookRepository.delete(book);
        } catch (NotFoundException e) {
            return errorResponse(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());

            return errorResponse("Something went wrong while deleting the book. Please try again later");
        }

        return successResponse();
    }

    public ResponseDto markBook(Long bookId) {
        try {
            Book book = findBookOrThrowException(bookId);

            Integer numCopies = book.getNumCopies();

            if (numCopies == 0) {
                return errorResponse("Cannot lower book copies when there are none left in stock");
            }

            book.setNumCopies(numCopies - 1);

            bookRepository.save(book);
        } catch (NotFoundException e) {
            return errorResponse(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());

            return errorResponse("Something went wrong while deleting the book. Please try again later");
        }

        return successResponse();
    }

    public Book findBookOrThrowException(Long bookId) throws NotFoundException {
        return bookRepository
            .findById(bookId)
            .orElseThrow(() -> new NotFoundException("Book was not found"));
    }

    private static List<FieldErrorDto> validateUpsertBook(UpsertBookDto upsertBookDto) {
        List<FieldErrorDto> errors = newArrayList();

        Long authorId = upsertBookDto.getAuthorId();
        UpsertAuthorDto upsertAuthorDto = upsertBookDto.getUpsertAuthorDto();

        if (authorId == null && upsertAuthorDto == null) {
            FieldErrorDto fieldErrorDto = new FieldErrorDto(
                "book",
                "",
                "Book must have either a present author or a new author set"
            );

            errors.add(fieldErrorDto);
        } else if (upsertAuthorDto != null) {
            errors.addAll(validateUpsertAuthor(upsertAuthorDto));
        }

        return errors;
    }

}
