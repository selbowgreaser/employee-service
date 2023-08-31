package org.selbowgreaser.employeeservice.builder;

import org.selbowgreaser.employeeservice.model.response.Result;
import org.selbowgreaser.employeeservice.model.type.Position;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@Component
public class EmployeeRequestResultBuilder {

    public Result buildEmployeeRequestResult(HttpStatus httpStatus) {
        return Result.builder()
                .timestamp(LocalDateTime.now())
                .status(httpStatus)
                .build();
    }
}
