package dev.senzalla.metakyasshuapi.settings.exception;

import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ResourceExceptionHandler {
    private final MessageSource messageSource;
    private final MessageDecode messageDecode;

    private String getMessage(RuntimeException ex) {
        if (ex.getCause() != null) {
            return messageSource.getMessage(ex.getMessage(), new String[]{ex.getCause().getMessage()}, LocaleContextHolder.getLocale());
        } else {
            return messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorDto> exceptionHandler(MethodArgumentNotValidException exception) {
        List<ErrorDto> errors = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            ErrorDto error = new ErrorDto(message, fieldError.getField(), HttpStatus.BAD_REQUEST);
            errors.add(error);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorDto handle(HttpMessageNotReadableException ex) {
        String message = messageDecode.info("error.not-body");
        return new ErrorDto(message, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorDto handle(NotFoundException ex) {
        return new ErrorDto(getMessage(ex), ex.getCause().getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserDisabledException.class)
    public ErrorDto handle(UserDisabledException ex) {
        return new ErrorDto(getMessage(ex), HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UserAuthenticateException.class)
    public ErrorDto handle(UserAuthenticateException ex) {
        return new ErrorDto(getMessage(ex), HttpStatus.FORBIDDEN);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateException.class)
    public ErrorDto handle(DuplicateException ex) {
        return new ErrorDto(getMessage(ex), HttpStatus.CONFLICT);
    }

}
