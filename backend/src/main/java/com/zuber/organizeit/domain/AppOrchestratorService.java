package com.zuber.organizeit.domain;

import com.zuber.organizeit.domain.Repository.EntityDAO;
import com.zuber.organizeit.domain.Task.Task;
import com.zuber.organizeit.domain.Task.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

import static java.util.Optional.ofNullable;

@Service
public class AppOrchestratorService implements DomainService {

    private final EntityDAO entityDAO;

    public AppOrchestratorService(EntityDAO entityDAO) {
        this.entityDAO = entityDAO;
    }

    public Task newTaskInShortTermPlan(TaskDTO dto) {
        var task = saveBasicData(dto);
        saveSubtask(dto, task);
        saveInPlan(dto, task);
        return task;
    }

    private Task saveBasicData(TaskDTO dto) {
        var task = ofNullable(dto.getId())
                        .flatMap(entityDAO::findTaskById)
                        .orElse(Task.newFromDto(dto))
                        .modifyBasicData(dto);
        if(task.getSubTasks() == null) task.setSubTasks(new LinkedList<>());
        task.getSubTasks().addAll(entityDAO.findTasks(
                        ofNullable(dto.getSubtaskIds()).orElseGet(LinkedList::new)
                                .stream().distinct().toList()));
        task = entityDAO.save(task);
        return task;
    }

    private void saveInPlan(TaskDTO dto, Task task) {
        ofNullable(dto.getPlanId()).flatMap(entityDAO::findPlanById)
                        .map(pln -> {
                            if(pln.getRootTasks() == null) pln.setRootTasks(new LinkedList<>());
                            pln.getRootTasks().add(task);
                            return pln;
                        }).ifPresent(entityDAO::save);
    }

    private void saveSubtask(TaskDTO dto, Task task) {
        ofNullable(dto.getParentTaskId())
                .flatMap(entityDAO::findTaskById)
                        .ifPresent(parent -> {
                            parent.getSubTasks().add(task);
                            entityDAO.save(parent);
                        });
    }


}
