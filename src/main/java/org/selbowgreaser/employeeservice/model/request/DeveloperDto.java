package org.selbowgreaser.employeeservice.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
public class DeveloperDto {

    @JsonProperty("framework")
    private String framework;

    @JsonProperty("database_skill")
    private String databaseSkill;

    @JsonProperty("version_control")
    private String versionControl;

}
