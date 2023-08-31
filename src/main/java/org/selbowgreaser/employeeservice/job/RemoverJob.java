package org.selbowgreaser.employeeservice.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.selbowgreaser.employeeservice.repository.EmployeeRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class RemoverJob {

    private final EmployeeRepository employeeRepository;

    @Scheduled(cron = "${app.cron-delete-random-employee}")
    public void deleteOneRow() {
        employeeRepository.deleteRandomEmployee();

        log.info("Random employee has been removed");
    }

}
