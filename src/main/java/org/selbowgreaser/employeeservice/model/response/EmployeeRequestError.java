package org.selbowgreaser.employeeservice.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@RequiredArgsConstructor
public class EmployeeRequestError extends Result {

    private List<FieldError> errors;

}
