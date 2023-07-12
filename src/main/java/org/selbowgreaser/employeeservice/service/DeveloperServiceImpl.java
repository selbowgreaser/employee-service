package org.selbowgreaser.employeeservice.service;

import lombok.RequiredArgsConstructor;
import org.selbowgreaser.employeeservice.model.Developer;
import org.selbowgreaser.employeeservice.repository.DeveloperRepository;
import org.selbowgreaser.employeeservice.service.api.DeveloperService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    @Override
    public Developer findDeveloperById(Long id) {
        return developerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void saveDeveloper(Developer developer) {
        developerRepository.save(developer);
    }

    @Override
    public void updateDeveloper(Developer updatedDeveloper) {
        developerRepository.save(updatedDeveloper);
    }

    @Override
    public void deleteDeveloperById(Long id) {
        if (!isExist(id)) {
            throw new IllegalArgumentException();
        }

        developerRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Long id) {
        return developerRepository.existsById(id);
    }
}
