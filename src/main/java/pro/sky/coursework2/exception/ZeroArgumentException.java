package pro.sky.coursework2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ZeroArgumentException extends IllegalArgumentException {
    public ZeroArgumentException(String message) {
        super(message);
    }
}
