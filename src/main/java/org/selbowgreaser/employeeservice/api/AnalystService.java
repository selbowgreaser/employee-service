package org.selbowgreaser.employeeservice.api;

import org.selbowgreaser.employeeservice.model.entity.position.Analyst;
import org.selbowgreaser.employeeservice.model.request.AnalystDto;
import org.selbowgreaser.employeeservice.model.request.EmployeeRequestDto;

import java.util.List;
import java.util.Map;


public interface AnalystService {

    /*
    TODO общие замечание
        я бы опустил слово Analyst, так как в рамках одного сервиса (у которого есть конкретная сущность) понятно тот или иной метод
        также при текущем наименовании идут разногласия в названиях: findAnalystById и findAll
     */

    void createAnalyst(EmployeeRequestDto<?> employeeRequestDto, Map<String, String> errors);

    List<Analyst> findAll();

    Analyst findAnalystById(Long id);

    void updateAnalyst(Long id, AnalystDto updatedAnalystDto, Map<String, String> errors);

    void deleteAnalystById(Long id);

    boolean isExist(Long id);
}
