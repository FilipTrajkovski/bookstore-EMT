package mk.ukim.finki.emt.bookstore.utils;

import mk.ukim.finki.emt.bookstore.dto.AuthorDto;
import mk.ukim.finki.emt.bookstore.dto.UpsertAuthorDto;
import mk.ukim.finki.emt.bookstore.model.Author;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class AuthorMapper {

    private AuthorMapper() {
    }

    public static Author toModel(UpsertAuthorDto upsertAuthorDto) {
        Author author = new Author();

        author.setName(upsertAuthorDto.getName());
        author.setSurname(upsertAuthorDto.getSurname());

        return author;
    }

    public static AuthorDto toDto(Author author) {
        return AuthorDto
            .builder()
            .id(author.getId())
            .name(author.getName())
            .surname(author.getSurname())
            .countryName(author.getCountry().getName())
            .build();
    }

    public static List<AuthorDto> toDtoList(List<Author> authors) {
        return authors
            .stream()
            .map(AuthorMapper::toDto)
            .collect(toList());
    }

}
