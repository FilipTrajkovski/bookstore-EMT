package mk.ukim.finki.emt.bookstore.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpsertBookResponseDto {

    private final boolean success;
    private final String errorMessage;

    private final UpsertBookDto upsertBookDto;
}
