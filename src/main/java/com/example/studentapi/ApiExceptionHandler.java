package com.example.studentapi;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> invalidJson() {
        return ResponseEntity.badRequest().body(Map.of(
                "error", "Invalid JSON body",
                "message", "Use Body > raw > JSON in Postman"));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Map<String, String>> unsupportedContentType() {
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(Map.of(
                "error", "Unsupported Content-Type",
                "message", "Use Content-Type: application/json, or x-www-form-urlencoded"));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, String>> databaseError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "error", "Database error",
                "message", "Check the configured database connection"));
    }
}
