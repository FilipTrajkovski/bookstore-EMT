package mk.ukim.finki.emt.bookstore.service;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.emt.bookstore.model.Author;
import mk.ukim.finki.emt.bookstore.repository.impl.AuthorRepository;
import mk.ukim.finki.emt.bookstore.utils.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author findAuthorOrThrowException(Long authorId) throws NotFoundException {
        return authorRepository
            .findById(authorId)
            .orElseThrow(() -> new NotFoundException("Author was not found"));
    }

}
