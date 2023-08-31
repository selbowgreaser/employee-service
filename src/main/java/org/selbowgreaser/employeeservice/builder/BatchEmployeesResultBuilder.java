package org.selbowgreaser.employeeservice.builder;

import lombok.RequiredArgsConstructor;
import org.selbowgreaser.employeeservice.model.response.BatchEmployeesResult;
import org.selbowgreaser.employeeservice.util.ListMapToBatchFieldErrorsMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@Component
@RequiredArgsConstructor
public class BatchEmployeesResultBuilder {

    private final ListMapToBatchFieldErrorsMapper listMapToBatchFieldErrorsMapper;

    public BatchEmployeesResult buildBatchEmployeeResult(List<Map<String, String>> allErrors) {
        return BatchEmployeesResult.builder()
                .timestamp(LocalDateTime.now())
                .status(CREATED)
                .message("Сотрудники, прошедшие валидацию, созданы!")
                .batchFieldErrors(listMapToBatchFieldErrorsMapper.mapListMapToBatchFieldErrors(allErrors))
                .build();
    }
}
