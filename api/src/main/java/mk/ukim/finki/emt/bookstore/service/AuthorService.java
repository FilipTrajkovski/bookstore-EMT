package mk.ukim.finki.emt.bookstore.service;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.emt.bookstore.dto.FieldErrorDto;
import mk.ukim.finki.emt.bookstore.dto.UpsertAuthorDto;
import mk.ukim.finki.emt.bookstore.dto.UpsertCountryDto;
import mk.ukim.finki.emt.bookstore.model.Author;
import mk.ukim.finki.emt.bookstore.model.Country;
import mk.ukim.finki.emt.bookstore.repository.impl.AuthorRepository;
import mk.ukim.finki.emt.bookstore.utils.AuthorMapper;
import mk.ukim.finki.emt.bookstore.utils.FieldsCannotBeNullOrEmptyException;
import mk.ukim.finki.emt.bookstore.utils.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static mk.ukim.finki.emt.bookstore.service.CountryService.validateUpsertCountry;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public Author saveAuthor(UpsertAuthorDto upsertAuthorDto) throws FieldsCannotBeNullOrEmptyException, NotFoundException {

        List<FieldErrorDto> fieldErrors = validateUpsertAuthor(upsertAuthorDto);

        if (!fieldErrors.isEmpty()) {
            throw new FieldsCannotBeNullOrEmptyException(fieldErrors);
        }

        Long countryId = upsertAuthorDto.getCountryId();
        UpsertCountryDto upsertCountryDto = upsertAuthorDto.getUpsertCountryDto();

        Country country = null;
        if (countryId != null) {
            country = countryService.findCountryOrThrowException(countryId);
        } else if (upsertCountryDto != null) {
            country = countryService.saveCountry(upsertCountryDto);
        }

        Author author = AuthorMapper.toModel(upsertAuthorDto);

        author.setCountry(country);

        return authorRepository.save(author);
    }

    public Author findAuthorOrThrowException(Long authorId) throws NotFoundException {
        return authorRepository
            .findById(authorId)
            .orElseThrow(() -> new NotFoundException("Author was not found"));
    }

    public static List<FieldErrorDto> validateUpsertAuthor(UpsertAuthorDto upsertAuthorDto) {
        List<FieldErrorDto> errors = newArrayList();

        Long countryId = upsertAuthorDto.getCountryId();
        UpsertCountryDto upsertCountryDto = upsertAuthorDto.getUpsertCountryDto();

        if (countryId == null && upsertCountryDto == null) {
            FieldErrorDto fieldErrorDto = new FieldErrorDto(
                "author",
                "",
                "Author must have either a present country or a new country set"
            );

            errors.add(fieldErrorDto);
        } else if (upsertCountryDto != null) {
            errors.addAll(validateUpsertCountry(upsertCountryDto));
        }

        String name = upsertAuthorDto.getName();
        String surname = upsertAuthorDto.getSurname();

        if (isEmpty(name)) {
            FieldErrorDto fieldErrorDto = new FieldErrorDto(
                "author",
                "name",
                "Author name cannot be null or empty"
            );

            errors.add(fieldErrorDto);
        }

        if (isEmpty(surname)) {
            FieldErrorDto fieldErrorDto = new FieldErrorDto(
                "author",
                "surname",
                "Author surname cannot be null or empty"
            );

            errors.add(fieldErrorDto);
        }

        return errors;
    }

}
