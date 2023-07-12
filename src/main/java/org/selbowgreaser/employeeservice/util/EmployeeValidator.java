package org.selbowgreaser.employeeservice.util;

import lombok.RequiredArgsConstructor;
import org.selbowgreaser.employeeservice.model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeValidator {

    public static final String EMAIL_TEMPLATE = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private final SalaryValidator salaryValidator;

    public List<String> validate(Employee employee) {
        List<String> errors = new ArrayList<>();

        validateName(errors, employee.getName());
        validateSurname(errors, employee.getSurname());
        validateDepartment(errors, employee.getDepartment());
        validateEmail(errors, employee.getEmail());

        salaryValidator.checkSalary(errors, employee.getPosition(), employee.getSalary());

        return errors;
    }

    private void validateName(List<String> errors, String name) {
        if (name.isBlank()) {
            errors.add("Имя не должно быть пустым");
        } else if (containsNotLetter(name)) {
            errors.add("Имя должно содержать только буквы");
        }
    }

    private void validateSurname(List<String> errors, String surname) {
        if (surname.isBlank()) {
            errors.add("Фамилия не должна быть пустой");
        } else if (containsNotLetter(surname)) {
            errors.add("Фамилия должна содержать только буквы");
        }
    }

    private void validateDepartment(List<String> errors, String department) {
        if (department.isBlank()) {
            errors.add("Название департамента не должно быть пустым");
        }
    }

    private void validateEmail(List<String> errors, String email) {
        if (!email.matches(EMAIL_TEMPLATE)) {
            errors.add("Email должен быть в формате: something42@example.com");
        }
    }

    private boolean containsNotLetter(String name) {
        return !name.matches("[A-Za-zА-Яа-я]+");
    }


}
