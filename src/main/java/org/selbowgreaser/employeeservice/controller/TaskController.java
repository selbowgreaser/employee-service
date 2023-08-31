package org.selbowgreaser.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.selbowgreaser.employeeservice.api.TaskService;
import org.selbowgreaser.employeeservice.model.entity.Task;
import org.selbowgreaser.employeeservice.model.request.TaskDto;
import org.selbowgreaser.employeeservice.model.response.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/api/v0/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Result> createTask(@RequestBody TaskDto taskDto) {

        log.info("Creating new task");

        taskService.save(mapTaskDtoToTask(taskDto));

        return new ResponseEntity<>(Result.builder()
                .timestamp(LocalDateTime.now())
                .status(CREATED)
                .build(), CREATED);
    }

    @GetMapping
    public List<Task> findAll() {
        log.info("Got the request of finding all tasks");

        return taskService.findAll();
    }

    @GetMapping("/{uuid}")
    public Task findOneByUuid(@PathVariable("uuid") UUID uuid) {
        log.info("Fetching task with UUID: {}", uuid);

        return taskService.findByUuid(uuid);
    }

    @GetMapping("/employee/{employee_id}")
    public List<Task> findAllByEmployeeId(@PathVariable("employee_id") Long employeeId) {
        log.info("Got the request of finding all tasks by employee");

        return taskService.findByEmployeeId(employeeId);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Result> updateTask(@PathVariable("uuid") UUID uuid,
                                             @RequestBody TaskDto updatedTaskDto) {
        log.info("Updating task with UUID: {}", uuid);

        Task updatedTask = mapTaskDtoToTask(updatedTaskDto);

        updatedTask.setUuid(uuid);

        taskService.update(updatedTask);

        log.info("The task with UUID {} has been successfully updated", uuid);

        return new ResponseEntity<>(Result.builder()
                .timestamp(LocalDateTime.now())
                .status(OK)
                .build(), OK);
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<Result> deleteTask(@PathVariable("uuid") UUID uuid) {
        log.info("Deleting task with UUID: {}", uuid);

        taskService.deleteByUUID(uuid);

        log.info("Task with UUID {} deleted successfully", uuid);

        return new ResponseEntity<>(Result.builder()
                .timestamp(LocalDateTime.now())
                .status(OK)
                .build(), OK);
    }

    private Task mapTaskDtoToTask(TaskDto taskDto) {
        return modelMapper.map(taskDto, Task.class);
    }

}
