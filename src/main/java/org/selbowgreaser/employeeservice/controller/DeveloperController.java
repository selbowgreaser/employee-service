package org.selbowgreaser.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.selbowgreaser.employeeservice.api.DeveloperService;
import org.selbowgreaser.employeeservice.builder.EmployeeRequestErrorBuilder;
import org.selbowgreaser.employeeservice.builder.EmployeeRequestResultBuilder;
import org.selbowgreaser.employeeservice.model.entity.position.Developer;
import org.selbowgreaser.employeeservice.model.request.DeveloperDto;
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
@RequestMapping("api/v0/developers")
@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;
    private final EmployeeRequestErrorBuilder employeeRequestErrorBuilder;
    private final EmployeeRequestResultBuilder employeeRequestResultBuilder;

    @PostMapping
    public ResponseEntity<?> createDeveloper(@RequestBody EmployeeRequestDto<DeveloperDto> employeeRequestDto) {

        log.info("Creating new developer");

        Map<String, String> errors = new HashMap<>();

        developerService.createDeveloper(employeeRequestDto, errors);

        if (errors.isEmpty()) {
            log.info("The developer has been successfully registered");
            return new ResponseEntity<>(employeeRequestResultBuilder.buildEmployeeRequestResult(CREATED), CREATED);
        }

        log.error("Request of developer registration contains errors");

        return new ResponseEntity<>(employeeRequestErrorBuilder.buildEmployeeRequestError(errors), BAD_REQUEST);
    }

    @GetMapping
    public List<Developer> findAll() {
        log.info("Got the request of finding all developers");

        return developerService.findAll();
    }

    @GetMapping("/{id}")
    public Developer findOne(@PathVariable("id") Long id) {
        log.info("Fetching developer with ID: {}", id);

        return developerService.findDeveloperById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable("id") Long id, DeveloperDto updatedDeveloperDto) {
        Map<String, String> errors = new HashMap<>();

        log.info("Updating developer with ID: {}", id);

        developerService.updateDeveloper(id, updatedDeveloperDto, errors);

        if (errors.isEmpty()) {
            log.info("The developer with ID {} has been successfully registered", id);
            return new ResponseEntity<>(employeeRequestResultBuilder.buildEmployeeRequestResult(OK), OK);
        }

        log.error("Request of developer registration contains errors");

        return new ResponseEntity<>(employeeRequestErrorBuilder.buildEmployeeRequestError(errors), BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteById(@PathVariable("id") Long id) {
        log.info("Deleting developer with ID: {}", id);

        developerService.deleteDeveloperById(id);

        log.info("Developer with ID {} deleted successfully", id);

        return new ResponseEntity<>(Result.builder().timestamp(LocalDateTime.now()).status(OK).build(), OK);
    }
}
