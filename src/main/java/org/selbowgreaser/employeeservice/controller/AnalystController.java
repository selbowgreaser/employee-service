package org.selbowgreaser.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.selbowgreaser.employeeservice.api.AnalystService;
import org.selbowgreaser.employeeservice.builder.EmployeeRequestErrorBuilder;
import org.selbowgreaser.employeeservice.builder.EmployeeRequestResultBuilder;
import org.selbowgreaser.employeeservice.model.entity.position.Analyst;
import org.selbowgreaser.employeeservice.model.request.AnalystDto;
import org.selbowgreaser.employeeservice.model.request.EmployeeRequestDto;
import org.selbowgreaser.employeeservice.model.response.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequestMapping("api/v0/analysts")
@RequiredArgsConstructor
public class AnalystController {

    private final AnalystService analystService;
    private final EmployeeRequestErrorBuilder employeeRequestErrorBuilder;
    private final EmployeeRequestResultBuilder employeeRequestResultBuilder;

    @PostMapping
    public ResponseEntity<?> createAnalyst(@RequestBody EmployeeRequestDto<?> employeeRequestDto) {

        log.info("Creating new analyst");

        Map<String, String> errors = new HashMap<>();

        analystService.createAnalyst(employeeRequestDto, errors);

        if (errors.isEmpty()) {
            log.info("The analyst has been successfully registered");
            return new ResponseEntity<>(employeeRequestResultBuilder.buildEmployeeRequestResult(CREATED), CREATED);
        }

        log.error("Request of analyst registration contains errors");

        return new ResponseEntity<>(employeeRequestErrorBuilder.buildEmployeeRequestError(errors), BAD_REQUEST);

    }

    @GetMapping
    public List<Analyst> findAll() {
        log.info("Got the request of finding all analysts");

        return analystService.findAll();
    }

    @GetMapping("/{id}")
    public Analyst findOne(@PathVariable("id") Long id) {
        log.info("Fetching analyst with ID: {}", id);

        return analystService.findAnalystById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable("id") Long id, AnalystDto updatedAnalystDto) {
        Map<String, String> errors = new HashMap<>();

        log.info("Updating analyst with ID: {}", id);

        analystService.updateAnalyst(id, updatedAnalystDto, errors);

        if (errors.isEmpty()) {
            log.info("The analyst with ID {} has been successfully updated", id);
            return new ResponseEntity<>(employeeRequestResultBuilder.buildEmployeeRequestResult(OK), OK);
        }

        log.error("Request of analyst update contains errors");

        return new ResponseEntity<>(employeeRequestErrorBuilder.buildEmployeeRequestError(errors), BAD_REQUEST);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteById(@PathVariable("id") Long id) {
        log.info("Deleting analyst with ID: {}", id);

        analystService.deleteAnalystById(id);

        log.info("Analyst with ID {} deleted successfully", id);

        return new ResponseEntity<>(Result.builder().timestamp(LocalDateTime.now()).status(OK).build(), OK);
    }
}
