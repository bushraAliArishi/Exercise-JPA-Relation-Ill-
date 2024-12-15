package com.example.school.ControllerAdviser;

import com.example.school.ApiResponse.ApiException;
import com.example.school.ApiResponse.ApiResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.MissingPathVariableException;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ControllerAdviser {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity handleApiException(ApiException e) {
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolation(ConstraintViolationException e) {
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage()));
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity handleSQLConstraintViolation(SQLIntegrityConstraintViolationException e) {
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage()));
    }

    @ExceptionHandler(value = InvalidDataAccessResourceUsageException.class)
    public ResponseEntity handleInvalidDataAccess(InvalidDataAccessResourceUsageException e) {
        return ResponseEntity.status(500).body(new ApiResponse(e.getMessage()));
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity handleDataIntegrityViolation(DataIntegrityViolationException e) {
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage()));
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(405).body(new ApiResponse(e.getMessage()));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        return ResponseEntity.status(400).body(new ApiResponse("Malformed JSON request: " + e.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.status(400).body(new ApiResponse("Argument type mismatch: " + e.getMessage()));
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity handleMissingRequestParameter(MissingServletRequestParameterException e) {
        return ResponseEntity.status(400).body(new ApiResponse("Missing request parameter: " + e.getParameterName()));
    }

    @ExceptionHandler(value = MissingPathVariableException.class)
    public ResponseEntity handleMissingPathVariable(MissingPathVariableException e) {
        return ResponseEntity.status(400).body(new ApiResponse("Missing path variable: " + e.getVariableName()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleGeneralException(Exception e) {
        return ResponseEntity.status(500).body(new ApiResponse("An unexpected error occurred: " + e.getMessage()));
    }
}