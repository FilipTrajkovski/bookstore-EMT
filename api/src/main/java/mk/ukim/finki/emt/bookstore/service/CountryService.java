package mk.ukim.finki.emt.bookstore.service;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.emt.bookstore.dto.CountryDto;
import mk.ukim.finki.emt.bookstore.dto.FieldErrorDto;
import mk.ukim.finki.emt.bookstore.dto.UpsertCountryDto;
import mk.ukim.finki.emt.bookstore.model.Country;
import mk.ukim.finki.emt.bookstore.repository.impl.CountryRepository;
import mk.ukim.finki.emt.bookstore.utils.CountryMapper;
import mk.ukim.finki.emt.bookstore.utils.FieldsCannotBeNullOrEmptyException;
import mk.ukim.finki.emt.bookstore.utils.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public List<CountryDto> getAllCountries() {
        List<Country> countries = countryRepository.findAll();

        return CountryMapper.toDtoList(countries);
    }

    public Country saveCountry(UpsertCountryDto upsertCountryDto) throws FieldsCannotBeNullOrEmptyException {

        List<FieldErrorDto> fieldErrors = validateUpsertCountry(upsertCountryDto);

        if (!fieldErrors.isEmpty()) {
            throw new FieldsCannotBeNullOrEmptyException(fieldErrors);
        }

        Country country = CountryMapper.toModel(upsertCountryDto);

        return countryRepository.save(country);
    }

    public Country findCountryOrThrowException(Long countryId) throws NotFoundException {
        return countryRepository
            .findById(countryId)
            .orElseThrow(() -> new NotFoundException("Country was not found"));
    }

    public static List<FieldErrorDto> validateUpsertCountry(UpsertCountryDto upsertCountryDto) {
        List<FieldErrorDto> errors = newArrayList();

        String name = upsertCountryDto.getName();
        String continent = upsertCountryDto.getContinent();

        if (isEmpty(name)) {
            FieldErrorDto fieldErrorDto = new FieldErrorDto(
                "country",
                "name",
                "Country name cannot be null or empty"
            );

            errors.add(fieldErrorDto);
        }

        if (isEmpty(continent)) {
            FieldErrorDto fieldErrorDto = new FieldErrorDto(
                "country",
                "continent",
                "Country continent cannot be null or empty"
            );

            errors.add(fieldErrorDto);
        }

        return errors;
    }

}
