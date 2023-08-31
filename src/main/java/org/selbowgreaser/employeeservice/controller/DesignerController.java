package org.selbowgreaser.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.selbowgreaser.employeeservice.api.DesignerService;
import org.selbowgreaser.employeeservice.builder.EmployeeRequestErrorBuilder;
import org.selbowgreaser.employeeservice.builder.EmployeeRequestResultBuilder;
import org.selbowgreaser.employeeservice.model.entity.position.Designer;
import org.selbowgreaser.employeeservice.model.request.DesignerDto;
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
@RequestMapping("api/v0/designers")
@RequiredArgsConstructor
public class DesignerController {

    private final DesignerService designerService;
    private final EmployeeRequestErrorBuilder employeeRequestErrorBuilder;
    private final EmployeeRequestResultBuilder employeeRequestResultBuilder;

    @PostMapping
    public ResponseEntity<?> createDesigner(@RequestBody EmployeeRequestDto<?> employeeRequestDto) {

        log.info("Creating new designer");

        Map<String, String> errors = new HashMap<>();

        designerService.createDesigner(employeeRequestDto, errors);

        log.info("Designer created successfully");

        if (errors.isEmpty()) {
            log.info("The designer has been successfully registered");
            return new ResponseEntity<>(employeeRequestResultBuilder.buildEmployeeRequestResult(CREATED), CREATED);
        }

        log.error("Request of designer registration contains errors");

        return new ResponseEntity<>(employeeRequestErrorBuilder.buildEmployeeRequestError(errors), BAD_REQUEST);
    }

    @GetMapping
    public List<Designer> findAll() {
        log.info("Got the request of finding all designers");

        return designerService.findAll();
    }

    @GetMapping("/{id}")
    public Designer findOne(@PathVariable("id") Long id) {
        log.info("Fetching designer with ID: {}", id);

        return designerService.findDesignerById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable("id") Long id, DesignerDto updatedDesignerDto) {
        Map<String, String> errors = new HashMap<>();

        log.info("Updating designer with ID: {}", id);

        designerService.updateDesigner(id, updatedDesignerDto, errors);

        if (errors.isEmpty()) {
            log.info("The designer with ID {} has been successfully updated", id);
            return new ResponseEntity<>(employeeRequestResultBuilder.buildEmployeeRequestResult(OK), OK);
        }

        log.error("Request of designer update contains errors");

        return new ResponseEntity<>(employeeRequestErrorBuilder.buildEmployeeRequestError(errors), BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteById(@PathVariable("id") Long id) {
        log.info("Deleting designer with ID: {}", id);

        designerService.deleteDesignerById(id);

        log.info("Analyst with ID {} deleted successfully", id);

        return new ResponseEntity<>(Result.builder().timestamp(LocalDateTime.now()).status(OK).build(), OK);
    }
}
