package org.selbowgreaser.employeeservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.selbowgreaser.employeeservice.api.EmployeeService;
import org.selbowgreaser.employeeservice.api.TaskService;
import org.selbowgreaser.employeeservice.exception.TaskLimitExceededException;
import org.selbowgreaser.employeeservice.model.entity.Employee;
import org.selbowgreaser.employeeservice.model.entity.Task;
import org.selbowgreaser.employeeservice.repository.MaxTasksPositionRepository;
import org.selbowgreaser.employeeservice.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final MaxTasksPositionRepository maxTasksPositionRepository;
    private final EmployeeService employeeService;

    @Override
    public List<Task> findAll() {
        log.info("Fetching all tasks");
        return taskRepository.findAll();
    }

    @Override
    public Task findByUuid(UUID uuid) {
        log.info("Fetching task by UUID: {}", uuid);
        return taskRepository.findById(uuid).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Task> findByEmployeeId(Long employeeId) {
        log.info("Fetching tasks by employee ID: {}", employeeId);
        Employee employee = employeeService.findEmployeeById(employeeId);
        return taskRepository.findByEmployee(employee);
    }

    @Override
    public void save(Task task) {
        log.info("Saving task");
        Employee employee = employeeService.findEmployeeById(task.getEmployee().getId());

        isTaskLimitExceeded(employee);

        taskRepository.save(task);
        log.info("Task saved successfully");
    }

    @Override
    public void update(Task updatedTask) {
        log.info("Updating task");

        Employee previousEmployee = findByUuid(updatedTask.getUuid()).getEmployee();

        if (updatedTask.getEmployee() == null) {
            updatedTask.setEmployee(previousEmployee);
        } else {
            Employee newEmployee = employeeService.findEmployeeById(updatedTask.getEmployee().getId());
            if (!previousEmployee.equals(newEmployee)) {
                isTaskLimitExceeded(newEmployee);
            }
        }

        taskRepository.save(updatedTask);
        log.info("Task updated successfully");
    }

    @Override
    public void deleteByUUID(UUID uuid) {
        log.info("Deleting task by UUID: {}", uuid);
        taskRepository.deleteById(uuid);
        log.info("Task deleted successfully");
    }

    private void isTaskLimitExceeded(Employee employee) {
        if (employee.getTasks() != null && employee.getTasks().size() >= maxTasksPositionRepository.findByPosition(employee.getPosition()).getMaxTasks()) {
            throw new TaskLimitExceededException(employee.getId());
        }
    }
}
