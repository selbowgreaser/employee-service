package org.selbowgreaser.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.selbowgreaser.employeeservice.api.ManagerService;
import org.selbowgreaser.employeeservice.builder.EmployeeRequestErrorBuilder;
import org.selbowgreaser.employeeservice.builder.EmployeeRequestResultBuilder;
import org.selbowgreaser.employeeservice.model.entity.position.Manager;
import org.selbowgreaser.employeeservice.model.request.EmployeeRequestDto;
import org.selbowgreaser.employeeservice.model.request.ManagerDto;
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
@RequestMapping("api/v0/managers")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;
    private final EmployeeRequestErrorBuilder employeeRequestErrorBuilder;
    private final EmployeeRequestResultBuilder employeeRequestResultBuilder;

    @PostMapping
    public ResponseEntity<?> createManager(@RequestBody EmployeeRequestDto<ManagerDto> employeeRequestDto) {

        log.info("Creating new manager");

        Map<String, String> errors = new HashMap<>();

        managerService.createManager(employeeRequestDto, errors);

        if (errors.isEmpty()) {
            log.info("The manager has been successfully registered");
            return new ResponseEntity<>(employeeRequestResultBuilder.buildEmployeeRequestResult(CREATED), CREATED);
        }

        log.error("Request of manager registration contains errors");

        return new ResponseEntity<>(employeeRequestErrorBuilder.buildEmployeeRequestError(errors), BAD_REQUEST);
    }

    @GetMapping
    public List<Manager> findAll() {
        log.info("Got the request of finding all managers");

        return managerService.findAll();
    }

    @GetMapping("/{id}")
    public Manager findOne(@PathVariable("id") Long id) {
        log.info("Fetching manager with ID: {}", id);

        return managerService.findManagerById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable("id") Long id, ManagerDto updatedManagerDto) {
        Map<String, String> errors = new HashMap<>();

        log.info("Updating manager with ID: {}", id);

        managerService.updateManager(id, updatedManagerDto, errors);

        if (errors.isEmpty()) {
            log.info("The manager with id {} has been successfully registered", id);
            return new ResponseEntity<>(employeeRequestResultBuilder.buildEmployeeRequestResult(OK), OK);
        }

        log.error("Request of manager registration contains errors");

        return new ResponseEntity<>(employeeRequestErrorBuilder.buildEmployeeRequestError(errors), BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteById(@PathVariable("id") Long id) {
        log.info("Deleting manager with ID: {}", id);

        managerService.deleteManagerById(id);

        log.info("Manager with ID {} deleted successfully", id);

        return new ResponseEntity<>(Result.builder().timestamp(LocalDateTime.now()).status(OK).build(), OK);
    }

}
