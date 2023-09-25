package org.selbowgreaser.employeeservice.util.validator;

import lombok.RequiredArgsConstructor;
import org.selbowgreaser.employeeservice.model.entity.SalaryRange;
import org.selbowgreaser.employeeservice.model.type.Position;
import org.selbowgreaser.employeeservice.repository.SalaryRangeRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class SalaryValidator { //todo лишняя зависимость. Надо настроить(при коммите есть настройка автоиморта)

    private final SalaryRangeRepository salaryRangeRepository;

    public void checkSalary(Map<String, String> errors, Position position, BigDecimal salary) {
        SalaryRange salaryRange = salaryRangeRepository.findByPosition(position).orElseThrow(IllegalArgumentException::new);

        if (isInSalaryRange(salary, salaryRange)) {
            errors.put("salary", MessageFormat.format("Заработная плата {0} должна быть в границе от {1} до {2}" +
                            ". Было получено {3}.",
                    position,
                    salaryRange.getMinSalary(),
                    salaryRange.getMaxSalary(),
                    salary));
        }
    }

    private boolean isInSalaryRange(BigDecimal salary, SalaryRange salaryRange) {
        return isLessThanBottomBound(salaryRange.getMinSalary(), salary) || isMoreThanUpperBound(salaryRange.getMaxSalary(), salary);
    }

    private boolean isMoreThanUpperBound(BigDecimal maxSalary, BigDecimal salary) {
        return salary.compareTo(maxSalary) > 0;
    }

    private boolean isLessThanBottomBound(BigDecimal minSalary, BigDecimal salary) {
        return salary.compareTo(minSalary) <= 0;
    }
}
