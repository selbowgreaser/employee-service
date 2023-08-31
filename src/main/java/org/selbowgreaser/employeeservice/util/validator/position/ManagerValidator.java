package org.selbowgreaser.employeeservice.util.validator.position;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.selbowgreaser.employeeservice.model.entity.position.Manager;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class ManagerValidator {

    public void validate(Manager manager, Map<String, String> errors) {
        log.debug("Validating manager: {}", manager);

        if (manager.getDepartment() != null) {
            validateDepartment(errors, manager.getDepartment());
        }

        if (manager.getResponsibility() != null) {
            validateResponsibility(errors, manager.getResponsibility());
        }

        if (!errors.isEmpty()) {
            log.warn("Validation errors for manager {}: {}", manager.getId(), errors);
        }
    }

    private void validateResponsibility(Map<String, String> errors, String responsibility) {
        log.debug("Validating responsibility: {}", responsibility);
        if (!StringUtils.isAlphanumeric(responsibility)) {
            errors.put("responsibility", "Ответственность может содержать только буквы и цифры!");
        }
    }

    private void validateDepartment(Map<String, String> errors, String department) {
        log.debug("Validating department: {}", department);
        if (!StringUtils.isAlphanumeric(department)) {
            errors.put("department", "Департамент может содержать только буквы и цифры!");
        }
    }


}
