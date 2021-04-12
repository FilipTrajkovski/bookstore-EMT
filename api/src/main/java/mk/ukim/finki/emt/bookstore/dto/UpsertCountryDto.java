package mk.ukim.finki.emt.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpsertCountryDto {

    private String name;
    private String continent;
}
