package mk.ukim.finki.emt.bookstore.utils;

import mk.ukim.finki.emt.bookstore.dto.FieldErrorDto;
import mk.ukim.finki.emt.bookstore.dto.ResponseDto;

import java.util.List;

public class ResponseMapper {

    private ResponseMapper() {
    }

    public static ResponseDto successResponse() {
        return ResponseDto
            .builder()
            .success(true)
            .build();
    }

    public static ResponseDto errorResponse(String message) {
        return ResponseDto
            .builder()
            .success(false)
            .message(message)
            .build();
    }

    public static ResponseDto withFieldErrors(List<FieldErrorDto> errors) {
        return ResponseDto
            .builder()
            .success(false)
            .message("Field errors occurred")
            .fieldErrors(errors)
            .build();
    }

}
