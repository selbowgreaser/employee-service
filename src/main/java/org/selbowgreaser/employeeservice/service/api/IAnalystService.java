package org.selbowgreaser.employeeservice.service.api;

import org.selbowgreaser.employeeservice.model.Analyst;

public interface IAnalystService {

    Analyst findAnalystById(Long id);

    void saveAnalyst(Analyst analyst);

    void updateAnalyst(Analyst updatedAnalyst);

    void deleteAnalystById(Long id);

    boolean isExist(Long id);
}
