package mk.ukim.finki.emt.bookstore.utils;

import mk.ukim.finki.emt.bookstore.dto.UpsertAuthorDto;
import mk.ukim.finki.emt.bookstore.model.Author;

public class AuthorMapper {

    private AuthorMapper() {
    }

    public static Author toModel(UpsertAuthorDto upsertAuthorDto) {
        Author author = new Author();

        author.setName(upsertAuthorDto.getName());
        author.setSurname(upsertAuthorDto.getSurname());

        return author;
    }
}
