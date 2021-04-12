package mk.ukim.finki.emt.bookstore.utils;

import mk.ukim.finki.emt.bookstore.dto.CountryDto;
import mk.ukim.finki.emt.bookstore.dto.UpsertCountryDto;
import mk.ukim.finki.emt.bookstore.model.Country;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CountryMapper {

    private CountryMapper() {
    }

    public static Country toModel(UpsertCountryDto upsertCountryDto) {
        Country country = new Country();

        country.setName(upsertCountryDto.getName());
        country.setContinent(upsertCountryDto.getContinent());

        return country;
    }

    public static CountryDto toDto(Country country) {
        return CountryDto
            .builder()
            .id(country.getId())
            .name(country.getName())
            .continent(country.getContinent())
            .build();
    }

    public static List<CountryDto> toDtoList(List<Country> countries) {
        return countries
            .stream()
            .map(CountryMapper::toDto)
            .collect(toList());
    }

}
