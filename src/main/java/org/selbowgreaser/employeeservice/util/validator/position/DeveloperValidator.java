package org.selbowgreaser.employeeservice.util.validator.position;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.selbowgreaser.employeeservice.model.entity.position.Designer;
import org.selbowgreaser.employeeservice.model.entity.position.Developer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class DeveloperValidator {

    public void validate(Developer developer, Map<String, String> errors) {
        log.debug("Validating developer: {}", developer);

        if (developer.getFramework() != null) {
            validateFramework(errors, developer.getFramework());
        }

        if (developer.getDatabaseSkill() != null) {
            validateDatabaseSkill(errors, developer.getDatabaseSkill());
        }

        if (developer.getVersionControl() != null) {
            validateVersionControl(errors, developer.getVersionControl());
        }

        if (!errors.isEmpty()) {
            log.warn("Validation errors for developer {}: {}", developer.getId(), errors);
        }
    }

    private void validateFramework(Map<String, String> errors, String framework) {
        log.debug("Validating framework: {}", framework);
        if (!StringUtils.isAlphanumeric(framework)) {
            errors.put("framework", "Фреймворк может содержать только буквы и цифры!");
        }
    }

    private void validateDatabaseSkill(Map<String, String> errors, String databaseSkill) {
        log.debug("Validating database skill: {}", databaseSkill);
        if (!StringUtils.isAlphanumeric(databaseSkill)) {
            errors.put("database_skill", "Уровень владения базами данных может содержать только буквы и цифры!");
        }
    }

    private void validateVersionControl(Map<String, String> errors, String versionControl) {
        log.debug("Validating version control: {}", versionControl);
        if (!StringUtils.isAlphanumeric(versionControl)) {
            errors.put("version_control", "Система контроля версий может содержать только буквы и цифры!");
        }
    }

}

