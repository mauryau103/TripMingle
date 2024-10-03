package com.trip.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trip.domains.response.AppResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

import static com.trip.constants.ErrorConstant.ERROR_MESSAGE;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {
    ObjectMapper mapper = new ObjectMapper();


    @ExceptionHandler(ValidatorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public AppResponse<String> handleValidatorException(final ValidatorException ex) {
        log.error("Validation error: {} ", ex.getMessage());
        return new AppResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null, ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public AppResponse<String> handleIllegalStateException(final IllegalStateException ex) {
        log.error("Illegal state exception error: {} ", ex.getMessage());
        return new AppResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null, ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public AppResponse<String> handleNotFoundException(final NotFoundException ex) {
        log.error("Not found error: {} ", ex.getMessage());
        return new AppResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null, ex.getMessage());
    }

    @ExceptionHandler(ModelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public AppResponse<String> handleNotFoundException(final ModelNotFoundException ex) {
        log.error("Model not found error: {} ", ex.getMessage());
        return new AppResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null, ex.getMessage());
    }

    @ExceptionHandler(PageableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public AppResponse<String> handlePageableException(final PageableException ex) {
        log.error("Pageable exception: {} ", ex.getMessage());
        return new AppResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
                null, ex.getMessage());
    }

    @ExceptionHandler(UnprocessableException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public AppResponse<String> handleUnProcessableException(final UnprocessableException ex) {
        log.error("Failed to process exception thrown; {} ", ex.getMessage());
        return new AppResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
                null, ex.getMessage());
    }

    @ExceptionHandler(UnsupportedTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public AppResponse<String> handleUnsupportedTypeException(final UnsupportedTypeException ex) {
        log.error("Unsupported extension type error: {} ", ex.getMessage());
        return new AppResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
                null, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public AppResponse<String> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException ex) {
        log.error("Bad request error: {} ", ex.getMessage());
        return new AppResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null,
                "invalid.identifier");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> handleException(MethodArgumentNotValidException e) {

        AppResponse<Map<String,String>> response = new AppResponse<>(HttpStatus.BAD_REQUEST.value(), "validation error", null, e.getBindingResult()
                .getAllErrors()
                .stream().map(FieldError.class::cast).collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
        try {
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(response));
        } catch (JsonProcessingException ex) {
            throw new UnprocessableException(ex.getMessage());
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> handleException(HttpMessageNotReadableException e) {
        AppResponse<String> response = new AppResponse<>(HttpStatus.BAD_REQUEST.value(), null, null, e.getMessage());
        try {
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(response));
        } catch (JsonProcessingException ex) {
            throw new UnprocessableException(ex.getMessage());
        }
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> handleException(IllegalArgumentException e) {
        log.error(e.getMessage());
        AppResponse<String> response = new AppResponse<>(HttpStatus.BAD_REQUEST.value(), "validation.error", null, "One or more Invalid Parameters");
        try {
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(response));
        } catch (JsonProcessingException ex) {
            throw new UnprocessableException(ex.getMessage());
        }
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<String> handleException(SQLException e) {
        AppResponse<String> response = new AppResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, null, ERROR_MESSAGE);
        try {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(response));
        } catch (JsonProcessingException ex) {
            throw new UnprocessableException(ex.getMessage());
        }
    }

    @ExceptionHandler(JpaSystemException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<String> handleException(JpaSystemException e) {
        try {
            log.error("Error in JpaSystemException {} ",e.getMessage());
            AppResponse<String> response = new AppResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, null, ERROR_MESSAGE);
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(response));
        } catch (JsonProcessingException ex) {
            throw new UnprocessableException(ex.getMessage());
        }
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<String> handleException(RuntimeException e) {
        try {
            AppResponse<String> response = new AppResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, null, e.getMessage());
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(response));
        } catch (JsonProcessingException ex) {
            throw new UnprocessableException(ex.getMessage());
        }
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<String> handleException(NullPointerException e) {
        try {
            AppResponse<String> response = new AppResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_MESSAGE, null, ERROR_MESSAGE);
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(response));
        } catch (JsonProcessingException ex) {
            throw new UnprocessableException(ex.getMessage());
        }
    }

    @ExceptionHandler(ModelValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> handleException(ModelValidationException e) {
        log.error(e.getMessage());
        AppResponse<Object> response = new AppResponse<>(HttpStatus.BAD_REQUEST.value(), "validation.error", null, e.getErrors());
        try {
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(response));
        } catch (JsonProcessingException ex) {
            throw new UnprocessableException(ex.getMessage());
        }
    }

//    @ExceptionHandler(value = {AccessDeniedException.class})
//    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
//    @ResponseBody
//    public AppResponse<String> handleAccessDeniedException(Exception ex) {
//        log.error("Exception in handleAccessDeniedException method: {}", ex.getMessage());
//        return new AppResponse<>(HttpStatus.UNPROCESSABLE_ENTITY.value(), "You don't have access",
//                null, "You don't have access");
//    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public AppResponse<String> handleValidatorException(final MissingRequestHeaderException ex) {
        log.error("Header missing error: {} ", ex.getMessage());
        return new AppResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null, ex.getMessage());
    }

    @ExceptionHandler(OtpAlreadySentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public AppResponse<String> handleOtpAlreadySentException(final OtpAlreadySentException ex) {
        log.error("Otp already sent error: {} ", ex.getMessage());
        return new AppResponse<>(HttpStatus.CONFLICT.value(), ex.getMessage(), null, ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<String> handleException(DataIntegrityViolationException e) {
        try {
            log.error("Error in DataIntegrityViolationException {} ",e.getMessage());
            AppResponse<String> response = new AppResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, null, ERROR_MESSAGE);
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(response));
        } catch (JsonProcessingException ex) {
            throw new UnprocessableException(ex.getMessage());
        }
    }

    @ExceptionHandler(UserVerificationFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<String> handleException(UserVerificationFailedException e) {
        try {
            AppResponse<String> response = new AppResponse<>(673, e.getMessage(), null, e.getMessage());
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(response));
        } catch (JsonProcessingException ex) {
            throw new UnprocessableException(ex.getMessage());
        }
    }
}
