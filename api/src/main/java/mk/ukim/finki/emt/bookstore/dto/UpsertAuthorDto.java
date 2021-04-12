package mk.ukim.finki.emt.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpsertAuthorDto {

    private Long countryId;
    private UpsertCountryDto upsertCountryDto;

    private String name;
    private String surname;
}
