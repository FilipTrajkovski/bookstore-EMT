package mk.ukim.finki.emt.bookstore.utils;


import mk.ukim.finki.emt.bookstore.dto.BookDto;
import mk.ukim.finki.emt.bookstore.dto.UpsertBookDto;
import mk.ukim.finki.emt.bookstore.model.Author;
import mk.ukim.finki.emt.bookstore.model.Book;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class BookMapper {

    private BookMapper() {
    }

    public static Book toModel(UpsertBookDto upsertBookDto, Author author) {
        return Book
            .builder()
            .name(upsertBookDto.getName())
            .category(upsertBookDto.getCategory())
            .author(author)
            .numCopies(upsertBookDto.getNumCopies())
            .build();
    }

    public static void fillModel(Book target, UpsertBookDto upsertBookDto, Author author) {
        target.setName(upsertBookDto.getName());
        target.setCategory(upsertBookDto.getCategory());
        target.setNumCopies(upsertBookDto.getNumCopies());
        target.setAuthor(author);
    }

    public static BookDto toDto(Book book) {
        return BookDto
            .builder()
            .id(book.getId())
            .name(book.getName())
            .authorName(book.getAuthor().getName())
            .authorCountry(book.getAuthor().getCountry().getContinent())
            .category(book.getCategory())
            .build();
    }

    public static List<BookDto> toDtoList(List<Book> books) {
        return books
            .stream()
            .map(BookMapper::toDto)
            .collect(toList());
    }

}
