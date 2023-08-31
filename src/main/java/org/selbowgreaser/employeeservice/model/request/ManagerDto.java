package org.selbowgreaser.employeeservice.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
public class ManagerDto {

    @JsonProperty("department")
    private String department;

    @JsonProperty("responsibility")
    private String responsibility;
}
