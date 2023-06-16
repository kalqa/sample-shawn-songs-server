package songify.song.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
public class SongControllerErrorHandler {

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public SongErrorResponseDto handleException(IndexOutOfBoundsException exception) {
        String message = exception.getMessage();
        log.error(message);
        return new SongErrorResponseDto(message, HttpStatus.NOT_FOUND);
    }
}
