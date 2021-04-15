package mk.ukim.finki.emt.bookstore.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BookResponseDto {

    private final Long totalElements;

    private final List<BookDto> books;

}
