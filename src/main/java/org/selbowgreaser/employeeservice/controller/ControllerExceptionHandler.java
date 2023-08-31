package org.selbowgreaser.employeeservice.controller;

import org.selbowgreaser.employeeservice.exception.TaskLimitExceededException;
import org.selbowgreaser.employeeservice.model.response.ErrorResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(TaskLimitExceededException.class)
    public ResponseEntity<?> handleTaskLimitExceededException(TaskLimitExceededException exception) {
        return new ResponseEntity<>(ErrorResult.builder()
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .status(BAD_REQUEST)
                .build(), BAD_REQUEST);
    }
}
