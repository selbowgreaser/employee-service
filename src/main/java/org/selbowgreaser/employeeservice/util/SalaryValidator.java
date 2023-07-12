package org.selbowgreaser.employeeservice.util;

import jakarta.annotation.PostConstruct;
import org.selbowgreaser.soap.api.employee_service.Position;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.selbowgreaser.soap.api.employee_service.Position.*;

@Component
public class SalaryValidator {

    public static final int BOTTOM_BOUND_INDEX = 0;
    public static final int UPPER_BOUND_INDEX = 1;
    private final Map<Position, List<BigDecimal>> salaryRanges = new HashMap<>();

    @PostConstruct
    public void initializationSalaryRanges() {
        salaryRanges.put(ANALYST, List.of(BigDecimal.valueOf(100000), BigDecimal.valueOf(180000))); //TODO такие значения лучше держать в БД [Будет сделано в рамках следующей задачи]
        salaryRanges.put(DESIGNER, List.of(BigDecimal.valueOf(80000), BigDecimal.valueOf(160000)));
        salaryRanges.put(DEVELOPER, List.of(BigDecimal.valueOf(120000), BigDecimal.valueOf(210000)));
        salaryRanges.put(MANAGER, List.of(BigDecimal.valueOf(180000), BigDecimal.valueOf(320000)));
        salaryRanges.put(ENGINEER, List.of(BigDecimal.valueOf(240000), BigDecimal.valueOf(370000)));
    }

    public void checkSalary(List<String> errors, Position position, BigDecimal salary) {
        List<BigDecimal> salaryRange = salaryRanges.get(position);


        if (isInSalaryRange(salary, salaryRange)) {
            errors.add(MessageFormat.format("Заработная плата {0} должна быть в границе от {1} до {2}" +
                            ". Было получено {3}.",
                    position,
                    salaryRange.get(BOTTOM_BOUND_INDEX),
                    salaryRange.get(BOTTOM_BOUND_INDEX),
                    salary));
        }
    }

    private boolean isInSalaryRange(BigDecimal salary, List<BigDecimal> salaryRange) {
        return isLessThanBottomBound(salaryRange, salary) || isMoreThanUpperBound(salaryRange, salary);
    }

    private boolean isMoreThanUpperBound(List<BigDecimal> salaryRange, BigDecimal salary) {
        return salary.compareTo(salaryRange.get(UPPER_BOUND_INDEX)) > 0;
    }

    private boolean isLessThanBottomBound(List<BigDecimal> salaryRange, BigDecimal salary) {
        return salary.compareTo(salaryRange.get(BOTTOM_BOUND_INDEX)) <= 0;
    }
}
