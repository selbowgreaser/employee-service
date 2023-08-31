package org.selbowgreaser.employeeservice.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
public class EngineerDto {

    @JsonProperty("programming_language")
    private String programmingLanguage;

    @JsonProperty("tool")
    private String tool;

    @JsonProperty("certification")
    private String certification;
}
