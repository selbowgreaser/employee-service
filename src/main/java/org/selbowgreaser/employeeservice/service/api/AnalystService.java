package org.selbowgreaser.employeeservice.service.api;

import org.selbowgreaser.employeeservice.model.Analyst;


/*
    TODO лучше убрать I в начале названий, особой информативности не несет. А инициализацию с перфиксом Default или Impl
 */
public interface AnalystService {

    Analyst findAnalystById(Long id);

    void saveAnalyst(Analyst analyst);

    void updateAnalyst(Analyst updatedAnalyst);

    void deleteAnalystById(Long id);

    boolean isExist(Long id);
}
