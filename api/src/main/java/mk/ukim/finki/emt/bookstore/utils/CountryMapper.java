package mk.ukim.finki.emt.bookstore.utils;

import mk.ukim.finki.emt.bookstore.dto.UpsertCountryDto;
import mk.ukim.finki.emt.bookstore.model.Country;

public class CountryMapper {

    private CountryMapper() {
    }

    public static Country toModel(UpsertCountryDto upsertCountryDto) {
        Country country = new Country();

        country.setName(upsertCountryDto.getName());
        country.setContinent(upsertCountryDto.getContinent());

        return country;
    }

}
