package org.selbowgreaser.employeeservice.exception;

public class TaskLimitExceededException extends RuntimeException {

    private Long employeeId; //todo не используется ? конструктор ниже обращается в конструктор (String message)

    public TaskLimitExceededException(Long employeeId) {
        super("Превышен максимальный лимит задач на сотруднике c id = " + employeeId);
    }

}
