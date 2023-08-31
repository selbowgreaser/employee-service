package org.selbowgreaser.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.selbowgreaser.employeeservice.api.EmployeeService;
import org.selbowgreaser.employeeservice.builder.BatchEmployeesResultBuilder;
import org.selbowgreaser.employeeservice.builder.EmployeeRequestErrorBuilder;
import org.selbowgreaser.employeeservice.builder.EmployeeRequestResultBuilder;
import org.selbowgreaser.employeeservice.model.entity.Employee;
import org.selbowgreaser.employeeservice.model.request.EmployeeRequestDto;
import org.selbowgreaser.employeeservice.model.response.BatchEmployeesResult;
import org.selbowgreaser.employeeservice.model.response.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequestMapping("/api/v0/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final BatchEmployeesResultBuilder batchEmployeesResultBuilder;
    private final EmployeeRequestResultBuilder employeeRequestResultBuilder;
    private final EmployeeRequestErrorBuilder employeeRequestErrorBuilder;

    @PostMapping("/batch")
    public ResponseEntity<BatchEmployeesResult> createBatchEmployees(@RequestBody List<EmployeeRequestDto<?>> employeeRequestDtos) {

        log.info("Creating batch of new employees");

        List<Map<String, String>> allErrors = new ArrayList<>();

        employeeService.createBatchEmployees(employeeRequestDtos, allErrors);

        log.info("Validated employees have been successfully registered");

        return new ResponseEntity<>(batchEmployeesResultBuilder.buildBatchEmployeeResult(allErrors), CREATED);
    }

    @GetMapping
    public List<Employee> findAll() {
        log.info("Got the request of finding all employees");

        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findOne(@PathVariable("id") Long id) {
        log.info("Fetching employee with ID: {}", id);

        return employeeService.findEmployeeById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable("id") Long id, EmployeeRequestDto<?> employeeRequestDto) {

        Map<String, String> errors = new HashMap<>();

        log.info("Updating employee with ID: {}", id);

        employeeService.updateEmployee(id, employeeRequestDto, errors);

        if (!errors.isEmpty()) {
            log.info("The employee with ID {} has been successfully updated", id);

            return new ResponseEntity<>(employeeRequestErrorBuilder.buildEmployeeRequestError(errors), BAD_REQUEST);
        }

        log.error("Request of employee update contains errors");

        return new ResponseEntity<>(employeeRequestResultBuilder.buildEmployeeRequestResult(OK), CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        log.info("Deleting employee with ID: {}", id);

        employeeService.deleteEmployeeById(id);

        log.info("Employee with ID {} deleted successfully", id);

        return new ResponseEntity<>(employeeRequestResultBuilder.buildEmployeeRequestResult(OK), OK);
    }
}
