package mk.ukim.finki.emt.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mk.ukim.finki.emt.bookstore.model.enums.Category;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UpsertBookDto {

    @NotNull
    @NotEmpty
    private final String name;

    @NotNull
    private final Category category;

    @NotNull
    private final Long authorId;

    @Min(0)
    @NotNull
    private final Integer numCopies;

}
