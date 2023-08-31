package org.selbowgreaser.employeeservice.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
public class AnalystDto {

    @JsonProperty("notation")
    private String notation;

    @JsonProperty("business_domain")
    private String businessDomain;

    @JsonProperty("requirements_gathering_technique")
    private String requirementsGatheringTechnique;
}
