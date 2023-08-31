package org.selbowgreaser.employeeservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.selbowgreaser.employeeservice.api.ManagerService;
import org.selbowgreaser.employeeservice.model.entity.Employee;
import org.selbowgreaser.employeeservice.model.entity.position.Manager;
import org.selbowgreaser.employeeservice.model.request.EmployeeRequestDto;
import org.selbowgreaser.employeeservice.model.request.ManagerDto;
import org.selbowgreaser.employeeservice.repository.ManagerRepository;
import org.selbowgreaser.employeeservice.util.validator.EmployeeValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ModelMapper modelMapper;
    private final ManagerRepository managerRepository;
    private final EmployeeValidator employeeValidator;

    @Override
    public void createManager(EmployeeRequestDto<?> employeeRequestDto, Map<String, String> errors) {
        log.info("Creating new manager");
        Employee employee = modelMapper.map(employeeRequestDto, Employee.class);

        Manager manager = modelMapper.map(employeeRequestDto.getPositionInformation(), Manager.class);

        employeeValidator.validate(employee, errors);

        if (errors.isEmpty()) {
            manager.setEmployee(employee);
            managerRepository.save(manager);
            log.info("Manager created successfully");
        }

    }

    @Override
    public List<Manager> findAll() {
        log.info("Fetching all managers");
        return managerRepository.findAll();
    }

    @Override
    public Manager findManagerById(Long id) {
        log.info("Fetching manager by id: {}", id);
        return managerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void updateManager(Long id, ManagerDto updatedManagerDto, Map<String, String> errors) {
        log.info("Updating manager with id: {}", id);
        Manager previousManager = findManagerById(id);

        Manager updatedManager = modelMapper.map(updatedManagerDto, Manager.class);

        updatedManager.setId(id);
        updatedManager.setEmployee(previousManager.getEmployee());

        managerRepository.save(updatedManager);
        log.info("Manager updated successfully");
    }

    @Override
    public void deleteManagerById(Long id) {
        log.info("Deleting manager with id: {}", id);
        if (!isExist(id)) {
            throw new IllegalArgumentException();
        }

        managerRepository.deleteById(id);
        log.info("Manager deleted successfully");
    }

    @Override
    public boolean isExist(Long id) {
        return managerRepository.existsById(id);
    }
}
