package songify.song.error;

import org.springframework.http.HttpStatus;

public record SongErrorResponseDto(
        String message,
        HttpStatus status) {
}
