package org.selbowgreaser.employeeservice.util.validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.selbowgreaser.employeeservice.model.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeValidator {

    public static final String EMAIL_TEMPLATE = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private final SalaryValidator salaryValidator;

    public void validate(Employee employee, Map<String, String> errors) {
        log.debug("Validating employee: {}", employee);

        validateName(errors, employee.getName());
        validateSurname(errors, employee.getSurname());
        validateDepartment(errors, employee.getDepartment());
        validateEmail(errors, employee.getEmail());

        salaryValidator.checkSalary(errors, employee.getPosition(), employee.getSalary());

        if (!errors.isEmpty()) {
            log.warn("Validation errors for employee {}: {}", employee.getId(), errors);
        }
    }

    private void validateName(Map<String, String> errors, String name) {
        log.debug("Validating name: {}", name);
        if (name.isBlank()) {
            errors.put("name", "Имя не должно быть пустым");
        } else if (containsNotLetter(name)) {
            errors.put("name", "Имя должно содержать только буквы");
        }
    }

    private void validateSurname(Map<String, String> errors, String surname) {
        log.debug("Validating surname: {}", surname);
        if (surname.isBlank()) {
            errors.put("surname", "Фамилия не должна быть пустой");
        } else if (containsNotLetter(surname)) {
            errors.put("surname", "Фамилия должна содержать только буквы");
        }
    }

    private void validateDepartment(Map<String, String> errors, String department) {
        log.debug("Validating department: {}", department);
        if (department.isBlank()) {
            errors.put("department", "Название департамента не должно быть пустым");
        }
    }

    private void validateEmail(Map<String, String> errors, String email) {
        log.debug("Validating email: {}", email);
        if (!email.matches(EMAIL_TEMPLATE)) {
            errors.put("email", "Email должен быть в формате: something42@example.com");
        }
    }

    private boolean containsNotLetter(String name) {
        return !name.matches("[A-Za-zА-Яа-я]+");
    }
}
