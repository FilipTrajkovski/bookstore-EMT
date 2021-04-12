package mk.ukim.finki.emt.bookstore.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.emt.bookstore.dto.AuthorDto;
import mk.ukim.finki.emt.bookstore.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        List<AuthorDto> authors = authorService.getAllAuthors();

        return ResponseEntity.ok(authors);
    }

}
