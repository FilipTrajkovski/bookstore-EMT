package mk.ukim.finki.emt.bookstore.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResponseDto {

    private final boolean success;
    private final String message;

    private final List<FieldErrorDto> fieldErrors;

}
