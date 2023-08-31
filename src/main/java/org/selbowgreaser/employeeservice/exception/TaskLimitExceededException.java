package org.selbowgreaser.employeeservice.exception;

public class TaskLimitExceededException extends RuntimeException {

    private Long employeeId;

    public TaskLimitExceededException(Long employeeId) {
        super("Превышен максимальный лимит задач на сотруднике c id = " + employeeId);
    }

}
