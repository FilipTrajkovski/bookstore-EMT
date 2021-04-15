package mk.ukim.finki.emt.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.bookstore.model.enums.Category;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpsertBookDto {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private Category category;

    private Long authorId;
    private UpsertAuthorDto upsertAuthorDto;

    @Min(0)
    @NotNull
    private Integer numCopies;

}
