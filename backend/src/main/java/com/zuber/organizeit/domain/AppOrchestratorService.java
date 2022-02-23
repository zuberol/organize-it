package com.zuber.organizeit.domain;

import com.zuber.organizeit.domain.Repository.EntityDAO;
import com.zuber.organizeit.domain.Task.Task;
import com.zuber.organizeit.domain.Task.TaskTO;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

import static java.util.Optional.ofNullable;

@Service
public class AppOrchestratorService implements DomainService {

    private final EntityDAO entityDAO;

    public AppOrchestratorService(EntityDAO entityDAO) {
        this.entityDAO = entityDAO;
    }

    public Task newTaskInShortTermPlan(TaskTO dto) {
        var task = saveBasicData(dto);
        saveSubtask(dto, task);
        saveInPlan(dto, task);
        return task;
    }

    private Task saveBasicData(TaskTO dto) {
        var task = ofNullable(dto.getId())
                        .flatMap(entityDAO::findTaskById)
                        .orElse(Task.newFromDto(dto))
                        .modifyBasicData(dto);
        task.setSubTasks(entityDAO.findTasks(
                ofNullable(dto.getSubTasksIds()).orElseGet(LinkedList::new)
                        .stream().distinct().toList()));
        task = entityDAO.save(task);
        return task;
    }

    private void saveInPlan(TaskTO dto, Task task) {
        ofNullable(dto.getPlanId()).flatMap(entityDAO::findPlanById)
                        .map(pln -> {
                            if(pln.getRootTasks() == null) pln.setRootTasks(new LinkedList<>());
                            pln.getRootTasks().add(task);
                            return pln;
                        }).ifPresent(entityDAO::save);
    }

    private void saveSubtask(TaskTO dto, Task task) {
        ofNullable(dto.getParentTaskId())
                .flatMap(entityDAO::findTaskById)
                        .ifPresent(parent -> {
                            parent.getSubTasks().add(task);
                            entityDAO.save(parent);
                        });
    }


}
