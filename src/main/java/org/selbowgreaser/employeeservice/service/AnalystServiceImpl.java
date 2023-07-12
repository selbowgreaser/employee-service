package org.selbowgreaser.employeeservice.service;

import lombok.RequiredArgsConstructor;
import org.selbowgreaser.employeeservice.model.Analyst;
import org.selbowgreaser.employeeservice.repository.AnalystRepository;
import org.selbowgreaser.employeeservice.service.api.AnalystService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnalystServiceImpl implements AnalystService {

    private final AnalystRepository analystRepository;

    @Override
    public Analyst findAnalystById(Long id) {
        return analystRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void saveAnalyst(Analyst analyst) {
        analystRepository.save(analyst);
    }

    @Override
    public void updateAnalyst(Analyst updatedAnalyst) {
        analystRepository.save(updatedAnalyst);
    }

    @Override
    public void deleteAnalystById(Long id) {
        if (!isExist(id)) {
            throw new IllegalArgumentException();
        }

        analystRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Long id) {
        return analystRepository.existsById(id);
    }
}
