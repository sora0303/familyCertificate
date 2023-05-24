package org.nhnacademy.family.advice;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.nhnacademy.family.exception.BirthDeathReportResidentNotFoundException;
import org.nhnacademy.family.exception.ErrorMessage;
import org.nhnacademy.family.exception.FamilyRelationshipNotFoundException;
import org.nhnacademy.family.exception.HouseholdMovementAddressNotFoundException;
import org.nhnacademy.family.exception.HouseholdNotFoundException;
import org.nhnacademy.family.exception.ResidentHouseholdException;
import org.nhnacademy.family.exception.ResidentNotFoundException;
import org.nhnacademy.family.exception.ValidationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class CommonRestControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }

    //400 Bad Request
    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentNotValidException.class,
        ValidationFailedException.class, ResidentHouseholdException.class})
    public ResponseEntity<ErrorMessage> missingParameter(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    //404 Not Found
    @ExceptionHandler({ResidentNotFoundException.class, FamilyRelationshipNotFoundException.class,
        BirthDeathReportResidentNotFoundException.class, HouseholdNotFoundException.class,
        HouseholdMovementAddressNotFoundException.class, NoHandlerFoundException.class})
    public ResponseEntity<ErrorMessage> eventNotFoundException(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    //405 Method Not Allowed
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ErrorMessage> methodNotAllowed(Exception exception, HttpServletRequest request) {
        ErrorMessage errorMessage =
            new ErrorMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), exception.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorMessage, HttpStatus.METHOD_NOT_ALLOWED);
    }


    //500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> internalServerError(Exception exception) {
        log.info("error : {}", exception);
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
