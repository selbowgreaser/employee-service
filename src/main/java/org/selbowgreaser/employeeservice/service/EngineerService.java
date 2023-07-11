package org.selbowgreaser.employeeservice.service;

import lombok.RequiredArgsConstructor;
import org.selbowgreaser.employeeservice.model.Engineer;
import org.selbowgreaser.employeeservice.repository.EngineerRepository;
import org.selbowgreaser.employeeservice.service.api.IEngineerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EngineerService implements IEngineerService {

    private final EngineerRepository engineerRepository;

    @Override
    public Engineer findEngineerById(Long id) {
        return engineerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void saveEngineer(Engineer engineer) {
        engineerRepository.save(engineer);
    }

    @Override
    public void updateEngineer(Engineer updatedEngineer) {
        engineerRepository.save(updatedEngineer);
    }

    @Override
    public void deleteEngineerById(Long id) {
        if (!isExist(id)) {
            throw new IllegalArgumentException();
        }

        engineerRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Long id) {
        return engineerRepository.existsById(id);
    }
}
