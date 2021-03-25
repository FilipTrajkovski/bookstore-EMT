package mk.ukim.finki.emt.bookstore.dto;

import lombok.Builder;
import lombok.Getter;
import mk.ukim.finki.emt.bookstore.model.enums.Category;

@Getter
@Builder
public class BookDto {

    private final Long id;

    private final String name;
    private final String authorName;
    private final String authorCountry;

    private final Category category;

    private final Integer numCopies;

}
