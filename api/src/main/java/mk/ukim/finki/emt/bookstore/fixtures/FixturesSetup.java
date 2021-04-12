package mk.ukim.finki.emt.bookstore.fixtures;

import mk.ukim.finki.emt.bookstore.model.Author;
import mk.ukim.finki.emt.bookstore.model.Country;
import mk.ukim.finki.emt.bookstore.repository.impl.AuthorRepository;
import mk.ukim.finki.emt.bookstore.repository.impl.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

@Component
public class FixturesSetup {

    @Bean
    CommandLineRunner initFixtures(AuthorRepository authorRepository,
                                   CountryRepository countryRepository) {
        return (evt) -> {
            List<Country> allCountries = countryRepository.findAll();
            List<Author> allAuthors = authorRepository.findAll();

            if (allCountries.isEmpty() && allAuthors.isEmpty()) {

                List<Country> countryFixtures = getCountryFixtures();

                List<Country> savedCountries = countryRepository.saveAll(countryFixtures);

                List<Author> authorFixtures = getAuthorFixtures();

                for (int i = 0; i < authorFixtures.size(); i++) {
                    Country country = savedCountries.get(i);

                    Author author = authorFixtures.get(i);

                    author.setCountry(country);
                }

                authorRepository.saveAll(authorFixtures);
            }
        };
    }

    private static List<Country> getCountryFixtures() {
        List<Country> countries = newArrayList();

        Country america = new Country();

        america.setName("America");
        america.setContinent("North America");

        countries.add(america);

        Country russia = new Country();

        russia.setName("Russia");
        russia.setContinent("Europe");

        countries.add(russia);

        Country england = new Country();

        england.setName("England");
        england.setContinent("Europe");

        countries.add(england);

        return countries;
    }

    private static List<Author> getAuthorFixtures() {
        List<Author> authors = newArrayList();

        Author lovecraft = new Author();

        lovecraft.setName("Howard");
        lovecraft.setSurname("Lovecraft");

        authors.add(lovecraft);

        Author dostoevsky = new Author();

        dostoevsky.setName("Fyodor");
        dostoevsky.setSurname("Dostoevsky");

        authors.add(dostoevsky);

        Author shakespeare = new Author();

        shakespeare.setName("William");
        shakespeare.setSurname("Shakespeare");

        authors.add(shakespeare);

        return authors;
    }
}
