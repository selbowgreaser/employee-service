package org.selbowgreaser.employeeservice.util.validator.position;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.selbowgreaser.employeeservice.model.entity.position.Designer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class DesignerValidator {

    public void validate(Designer designer, Map<String, String> errors) {
        log.debug("Validating designer: {}", designer);

        if (designer.getDesignTool() != null) {
            validateDesignTool(errors, designer.getDesignTool());
        }

        if (designer.getUxSkill() != null) {
            validateUsSkill(errors, designer.getUxSkill());
        }

        if (!errors.isEmpty()) {
            log.warn("Validation errors for designer {}: {}", designer.getId(), errors);
        }
    }


    private void validateDesignTool(Map<String, String> errors, String designTool) {
        log.debug("Validating business domain: {}", designTool);
        if (!StringUtils.isAlphanumeric(designTool)) {
            errors.put("business_domain", "Инструмент для дизайна может содержать только буквы и цифры!");
        }
    }

    private void validateUsSkill(Map<String, String> errors, String uxSkill) {
        log.debug("Validating ux skill: {}", uxSkill);
        if (!StringUtils.isAlphanumeric(uxSkill)) {
            errors.put("ux_skill", "UX навык может содержать только буквы и цифры!");
        }
    }
}
