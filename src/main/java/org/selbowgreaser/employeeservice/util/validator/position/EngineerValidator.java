package org.selbowgreaser.employeeservice.util.validator.position;

import jdk.jfr.Category;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.selbowgreaser.employeeservice.model.entity.position.Developer;
import org.selbowgreaser.employeeservice.model.entity.position.Engineer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class EngineerValidator { //todo лишняя зависимость. Надо настроить(при коммите есть настройка автоиморта)

    public void validate(Engineer engineer, Map<String, String> errors) {
        log.debug("Validating engineer: {}", engineer);

        if (engineer.getCertification() != null) {
            validateCertification(errors, engineer.getCertification());
        }

        if (engineer.getTool() != null) {
            validateTool(errors, engineer.getTool());
        }

        if (engineer.getProgrammingLanguage() != null) {
            validateProgrammingLanguage(errors, engineer.getProgrammingLanguage());
        }

        if (!errors.isEmpty()) {
            log.warn("Validation errors for engineer {}: {}", engineer.getId(), errors);
        }
    }

    private void validateProgrammingLanguage(Map<String, String> errors, String programmingLanguage) {
        log.debug("Validating programming language: {}", programmingLanguage);
        if (programmingLanguage.length() > 16) {
            errors.put("programming_language", "Язык программирования не должен быть длиннее 16 символов!");
        }
    }

    private void validateTool(Map<String, String> errors, String tool) {
        log.debug("Validating tool: {}", tool);
        if (tool.length() > 16) {
            errors.put("tool", "Инструмент разработки не должен быть длиннее 16 символов!");
        }
    }

    private void validateCertification(Map<String, String> errors, String certification) {
        log.debug("Validating tool: {}", certification);
        if (certification.length() > 16) {
            errors.put("tool", "Сертификат не должен быть длиннее 16 символов!");
        }
    }
}
