package org.selbowgreaser.employeeservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BatchFieldError {

    @JsonProperty("request_number") //todo зачем snake_case ?
    private Long requestNumber;

    @JsonProperty("errors")
    private List<FieldError> errors;
}
