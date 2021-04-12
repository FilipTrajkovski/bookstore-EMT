package mk.ukim.finki.emt.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldErrorDto {

    private final String model;
    private final String field;
    private final String errorMessage;
}
