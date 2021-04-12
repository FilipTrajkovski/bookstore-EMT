package mk.ukim.finki.emt.bookstore.utils;

import lombok.Getter;
import mk.ukim.finki.emt.bookstore.dto.FieldErrorDto;

import java.util.List;

@Getter
public class FieldsCannotBeNullOrEmptyException extends Exception {

    private final List<FieldErrorDto> fieldErrors;

    public FieldsCannotBeNullOrEmptyException(List<FieldErrorDto> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
