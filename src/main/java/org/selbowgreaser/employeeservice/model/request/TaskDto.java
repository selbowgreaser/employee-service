package org.selbowgreaser.employeeservice.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TaskDto {

    @JsonProperty("description")
    private String description;

    @JsonProperty("employee_id")
    private Long employeeId;
}
