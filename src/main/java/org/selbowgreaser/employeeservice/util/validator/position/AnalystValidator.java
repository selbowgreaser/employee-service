package org.selbowgreaser.employeeservice.util.validator.position;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.selbowgreaser.employeeservice.model.entity.position.Analyst;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class AnalystValidator {

    public void validate(Analyst analyst, Map<String, String> errors) {
        log.debug("Validating analyst: {}", analyst);

        if (analyst.getBusinessDomain() != null) {
            validateBusinessDomain(errors, analyst.getBusinessDomain());
        }

        if (analyst.getNotation() != null) {
            validateNotation(errors, analyst.getNotation());
        }

        if (analyst.getRequirementGatheringTechnique() != null) {
            validateRequirementGatheringTechnique(errors, analyst.getRequirementGatheringTechnique());
        }

        if (!errors.isEmpty()) {
            log.warn("Validation errors for analyst {}: {}", analyst.getId(), errors);
        }
    }

    private void validateBusinessDomain(Map<String, String> errors, String businessDomain) {
        log.debug("Validating business domain: {}", businessDomain);
        if (!StringUtils.isAlphanumeric(businessDomain)) {
            errors.put("business_domain", "Бизнес домен может содержать только буквы и цифры!");
        }
    }

    private void validateNotation(Map<String, String> errors, String notation) {
        log.debug("Validating notation: {}", notation);
        if (!StringUtils.isAlphanumeric(notation)) {
            errors.put("notation", "Нотация может содержать только буквы и цифры!");
        }
    }

    private void validateRequirementGatheringTechnique(Map<String, String> errors, String requirementGatheringTechnique) {
        log.debug("Validating requirement gathering technique: {}", requirementGatheringTechnique);
        if (!StringUtils.isAlphanumeric(requirementGatheringTechnique)) {
            errors.put("requirement_gathering_technique", "Техника сбора требований может содержать только буквы и цифры!");
        }
    }
}
