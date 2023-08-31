package org.selbowgreaser.employeeservice.builder;

import lombok.RequiredArgsConstructor;
import org.selbowgreaser.employeeservice.model.response.EmployeeRequestError;
import org.selbowgreaser.employeeservice.util.MapToFieldErrorsMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
@RequiredArgsConstructor
public class EmployeeRequestErrorBuilder {

    private final MapToFieldErrorsMapper mapToFieldErrorsMapper;

    public EmployeeRequestError buildEmployeeRequestError(Map<String, String> errors) {
        return EmployeeRequestError.builder()
                .timestamp(LocalDateTime.now())
                .status(BAD_REQUEST)
                .errors(mapToFieldErrorsMapper.mapMapToFieldErrors(errors))
                .build();
    }
}
