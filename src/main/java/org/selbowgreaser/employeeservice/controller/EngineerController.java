package org.selbowgreaser.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.selbowgreaser.employeeservice.api.EngineerService;
import org.selbowgreaser.employeeservice.builder.EmployeeRequestErrorBuilder;
import org.selbowgreaser.employeeservice.builder.EmployeeRequestResultBuilder;
import org.selbowgreaser.employeeservice.model.entity.position.Engineer;
import org.selbowgreaser.employeeservice.model.request.EmployeeRequestDto;
import org.selbowgreaser.employeeservice.model.request.EngineerDto;
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
@RequestMapping("api/v0/engineers")
@RequiredArgsConstructor
public class EngineerController {

    private final EngineerService engineerService;
    private final EmployeeRequestErrorBuilder employeeRequestErrorBuilder;
    private final EmployeeRequestResultBuilder employeeRequestResultBuilder;

    @PostMapping
    public ResponseEntity<?> createEngineer(@RequestBody EmployeeRequestDto<?> employeeRequestDto) {

        log.info("Creating new engineer");

        Map<String, String> errors = new HashMap<>();

        engineerService.createEngineer(employeeRequestDto, errors);

        if (errors.isEmpty()) {
            log.info("The engineer has been successfully registered");
            return new ResponseEntity<>(employeeRequestResultBuilder.buildEmployeeRequestResult(CREATED), CREATED);
        }

        log.error("Request of engineer registration contains errors");

        return new ResponseEntity<>(employeeRequestErrorBuilder.buildEmployeeRequestError(errors), BAD_REQUEST);
    }

    @GetMapping
    public List<Engineer> findAll() {
        log.info("Got the request of finding all engineers");

        return engineerService.findAll();
    }

    @GetMapping("/{id}")
    public Engineer findOne(@PathVariable("id") Long id) {
        log.info("Fetching engineer with ID: {}", id);

        return engineerService.findEngineerById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable("id") Long id, EngineerDto updatedEngineerDto) {
        Map<String, String> errors = new HashMap<>();

        log.info("Updating engineer with ID: {}", id);

        engineerService.updateEngineer(id, updatedEngineerDto, errors);

        if (errors.isEmpty()) {
            log.info("The engineer with id {} has been successfully registered", id);
            return new ResponseEntity<>(employeeRequestResultBuilder.buildEmployeeRequestResult(OK), OK);
        }

        log.error("Request of engineer registration contains errors");

        return new ResponseEntity<>(employeeRequestErrorBuilder.buildEmployeeRequestError(errors), BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteById(@PathVariable("id") Long id) {
        log.info("Deleting engineer with ID: {}", id);

        engineerService.deleteEngineerById(id);

        log.info("Engineer with ID {} deleted successfully", id);

        return new ResponseEntity<>(Result.builder().timestamp(LocalDateTime.now()).status(OK).build(), OK);
    }
}
