package mk.ukim.finki.emt.bookstore.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.emt.bookstore.dto.BookResponseDto;
import mk.ukim.finki.emt.bookstore.dto.ResponseDto;
import mk.ukim.finki.emt.bookstore.dto.UpsertBookDto;
import mk.ukim.finki.emt.bookstore.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/books")
public class BookController {

    private final BookService bookService;

    @GetMapping(path = "/page/{page}")
    @ResponseBody
    public ResponseEntity<BookResponseDto> getBooks(@PathVariable Integer page) {
        BookResponseDto responseDto = bookService.getAllBooksInPage(page);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ResponseDto> addBook(@RequestBody UpsertBookDto upsertBookDto) {
        ResponseDto responseDto = bookService.addBook(upsertBookDto);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<ResponseDto> editBook(@PathVariable Long id, @RequestBody UpsertBookDto upsertBookDto) {
        ResponseDto responseDto = bookService.editBook(id, upsertBookDto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<ResponseDto> deleteBook(@PathVariable Long id) {
        ResponseDto responseDto = bookService.deleteBook(id);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(path = "/mark/{id}")
    @ResponseBody
    public ResponseEntity<ResponseDto> markBook(@PathVariable Long id) {
        ResponseDto responseDto = bookService.markBook(id);

        return ResponseEntity.ok(responseDto);
    }

}
