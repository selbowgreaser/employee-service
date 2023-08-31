package org.selbowgreaser.employeeservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@RequiredArgsConstructor
public class Result {

    private LocalDateTime timestamp;
    private HttpStatus status;

}
