package org.selbowgreaser.employeeservice.model.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@RequiredArgsConstructor
public class BatchEmployeesResult extends Result {

    @JsonProperty
    private String message;

    @JsonProperty("batch_field_errors")
    private List<BatchFieldError> batchFieldErrors;

}
