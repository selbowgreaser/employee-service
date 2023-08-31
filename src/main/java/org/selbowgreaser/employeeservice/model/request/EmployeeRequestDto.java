package org.selbowgreaser.employeeservice.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.selbowgreaser.employeeservice.model.type.Education;
import org.selbowgreaser.employeeservice.model.type.EmploymentStatus;
import org.selbowgreaser.employeeservice.model.type.Gender;
import org.selbowgreaser.employeeservice.model.type.Position;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
public class EmployeeRequestDto<T> {

    @JsonProperty("surname")
    private String surname;

    @NotBlank
    @JsonProperty("name")
    private String name;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("position")
    private Position position;

    @NotBlank
    @JsonProperty("department")
    private String department;

    @Email
    @JsonProperty("email")
    private String email;

    @JsonProperty("salary")
    private BigDecimal salary;

    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @JsonProperty("education")
    private Education education;

    @JsonProperty("employment_status")
    private EmploymentStatus employmentStatus;

    @JsonProperty("position_information")
    private T positionInformation;
}
