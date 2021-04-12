package mk.ukim.finki.emt.bookstore.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthorDto {

    private final Long id;

    private final String name;
    private final String surname;
    private final String countryName;
}
