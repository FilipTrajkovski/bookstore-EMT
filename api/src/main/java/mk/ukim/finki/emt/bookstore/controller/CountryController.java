package mk.ukim.finki.emt.bookstore.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.emt.bookstore.dto.CountryDto;
import mk.ukim.finki.emt.bookstore.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/countries")
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryDto>> getAllCountries() {
        List<CountryDto> countries = countryService.getAllCountries();

        return ResponseEntity.ok(countries);
    }

}
