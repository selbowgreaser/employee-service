package org.selbowgreaser.employeeservice.service.api;

import org.selbowgreaser.employeeservice.model.Designer;

public interface IDesignerService {

    Designer findDesignerById(Long id);

    void saveDesigner(Designer designer);

    void updateDesigner(Designer updatedDesigner);

    void deleteDesignerById(Long id);

    boolean isExist(Long id);
}
