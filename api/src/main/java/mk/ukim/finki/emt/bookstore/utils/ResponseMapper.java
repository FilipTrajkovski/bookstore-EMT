package mk.ukim.finki.emt.bookstore.utils;

import mk.ukim.finki.emt.bookstore.dto.ResponseDto;

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

}
