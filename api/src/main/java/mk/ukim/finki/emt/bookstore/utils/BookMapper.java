package mk.ukim.finki.emt.bookstore.utils;


import mk.ukim.finki.emt.bookstore.dto.BookDto;
import mk.ukim.finki.emt.bookstore.dto.UpsertBookDto;
import mk.ukim.finki.emt.bookstore.model.Author;
import mk.ukim.finki.emt.bookstore.model.Book;

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
            .numCopies(book.getNumCopies())
            .build();
    }

    public static UpsertBookDto toUpsertDto(Book book) {
        return UpsertBookDto
            .builder()
            .name(book.getName())
            .category(book.getCategory())
            .authorId(book.getAuthor().getId())
            .numCopies(book.getNumCopies())
            .build();
    }

}
