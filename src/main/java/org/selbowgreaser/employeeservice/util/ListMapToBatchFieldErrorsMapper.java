package org.selbowgreaser.employeeservice.util;

import lombok.RequiredArgsConstructor;
import org.selbowgreaser.employeeservice.model.response.BatchFieldError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ListMapToBatchFieldErrorsMapper {

    private final MapToFieldErrorsMapper mapToFieldErrorsMapper;

    public List<BatchFieldError> mapListMapToBatchFieldErrors(List<Map<String, String>> allErrors) {
        List<BatchFieldError> batchFieldErrors = new ArrayList<>();

        long counter = 1;
        for (Map<String, String> errors : allErrors) {
            if (errors.isEmpty()) {
                counter++;
                continue;
            }

            BatchFieldError batchFieldError = BatchFieldError.builder()
                    .requestNumber(counter++) //todo это зачем ?
                    .errors(mapToFieldErrorsMapper.mapMapToFieldErrors(errors))
                    .build();
            batchFieldErrors.add(batchFieldError);
        }

        return batchFieldErrors;
    }
}
