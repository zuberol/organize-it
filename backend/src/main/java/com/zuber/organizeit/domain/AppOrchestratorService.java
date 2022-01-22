package com.zuber.organizeit.domain;

import com.zuber.organizeit.domain.Repository.EntityDAO;
import com.zuber.organizeit.domain.Task.Task;
import com.zuber.organizeit.domain.Task.TaskDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class AppOrchestratorService implements DomainService {

    private final EntityDAO entityDAO;

    public AppOrchestratorService(EntityDAO entityDAO) {
        this.entityDAO = entityDAO;
    }

    public Task newTaskInShortTermPlan(TaskDTO dto) {
        var task = Task.newFromDto(dto);
        ofNullable(task.getSubTasks()).orElseGet(LinkedList::new)
                .addAll(entityDAO.findTasks(
                        ofNullable(dto.getSubtaskIds()).orElseGet(LinkedList::new)
                                .stream().distinct().toList()));
//        var tags = ofNullable(dto.getTags())
//                .orElseGet(LinkedList::new)
//                .stream().map(String::toLowerCase).map(String::trim)
//                .map(str -> Tag.builder().mainName())
//                .toList();
//        task.setTags();

        ofNullable(dto.getPlanId()).flatMap(entityDAO::findPlanById)
                        .map(pln -> {
                            if(pln.getRootTasks() == null) pln.setRootTasks(new LinkedList<>());
                            pln.getRootTasks().add(task);
                            return pln;
                        }).ifPresent(entityDAO::save);
        return entityDAO.save(task);
    }



}
