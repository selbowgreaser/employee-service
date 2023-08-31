package org.selbowgreaser.employeeservice.api;

import org.selbowgreaser.employeeservice.model.entity.Employee;
import org.selbowgreaser.employeeservice.model.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<Task> findAll();

    Task findByUuid(UUID uuid);

    List<Task> findByEmployeeId(Long employeeId);

    void save(Task task);

    void update(Task updatedTask);

    void deleteByUUID(UUID uuid);
}
