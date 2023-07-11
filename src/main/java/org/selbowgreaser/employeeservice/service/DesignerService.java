package org.selbowgreaser.employeeservice.service;

import lombok.RequiredArgsConstructor;
import org.selbowgreaser.employeeservice.model.Designer;
import org.selbowgreaser.employeeservice.repository.DesignerRepository;
import org.selbowgreaser.employeeservice.service.api.IDesignerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DesignerService implements IDesignerService {

    private final DesignerRepository designerRepository;

    @Override
    public Designer findDesignerById(Long id) {
        return designerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void saveDesigner(Designer designer) {
        designerRepository.save(designer);
    }

    @Override
    public void updateDesigner(Designer updatedDesigner) {
        designerRepository.save(updatedDesigner);
    }

    @Override
    public void deleteDesignerById(Long id) {

        if (!isExist(id)) {
            throw new IllegalArgumentException();
        }

        designerRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Long id) {
        return designerRepository.existsById(id);
    }
}
