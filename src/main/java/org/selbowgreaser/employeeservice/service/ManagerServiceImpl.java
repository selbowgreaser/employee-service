package org.selbowgreaser.employeeservice.service;

import lombok.RequiredArgsConstructor;
import org.selbowgreaser.employeeservice.model.Manager;
import org.selbowgreaser.employeeservice.repository.ManagerRepository;
import org.selbowgreaser.employeeservice.service.api.ManagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;

    @Override
    public Manager findManagerById(Long id) {
        return managerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void saveManager(Manager manager) {
        managerRepository.saveAndFlush(manager);
    }

    @Override
    public void updateManager(Manager updatedManager) {
        managerRepository.save(updatedManager);
    }

    @Override
    public void deleteManagerById(Long id) {
        if (!isExist(id)) {
            throw new IllegalArgumentException();
        }

        managerRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Long id) {
        return managerRepository.existsById(id);
    }
}
