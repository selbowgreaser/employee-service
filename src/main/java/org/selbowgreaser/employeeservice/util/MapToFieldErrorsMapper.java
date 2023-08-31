package org.selbowgreaser.employeeservice.util;

import lombok.RequiredArgsConstructor;
import org.selbowgreaser.employeeservice.model.response.FieldError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MapToFieldErrorsMapper {

    public List<FieldError> mapMapToFieldErrors(Map<String, String> errors) {
        List<FieldError> fieldErrors = new ArrayList<>();

        for (Map.Entry<String, String> entry : errors.entrySet()) {
            FieldError fieldError = new FieldError(entry.getKey(), entry.getValue());
            fieldErrors.add(fieldError);
        }

        return fieldErrors;
    }
}
