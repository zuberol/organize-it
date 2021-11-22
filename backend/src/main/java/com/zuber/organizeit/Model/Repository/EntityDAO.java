package com.zuber.organizeit.Model.Repository;

import com.zuber.organizeit.Model.Task.Task;
import com.zuber.organizeit.Model.Task.TaskDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
public class EntityDAO {

    final TaskRepository taskRepository;
    final EntityManager em;

    @Autowired
    public EntityDAO(TaskRepository taskRepository, EntityManager em) {
        this.taskRepository = taskRepository;
        this.em = em;
    }

    public Optional<TaskDto> findById(TaskDto taskDto) {
        return ofNullable(taskDto)
                .map(TaskDto::getTaskId)
                .flatMap(id -> ofNullable(em.find(Task.class, id)))
                .filter(Task::isNotArchived)
                .map(Task::toDTO);
    }

    public List<TaskDto> findAllNonArchivedProjects() {
        return taskRepository.findAll()
                .stream()
                .filter(Task::isProject).filter(Task::isNotArchived)
                .map(Task::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<TaskDto> modifyTask(TaskDto dto) {
        return Optional.of(dto)
                .flatMap(taskDto -> ofNullable(taskDto.getTaskId()))
                .flatMap(taskRepository::findById)
                .map(task -> task.modify(dto))
                .map(Task::toDTO);
    }

    public Optional<TaskDto> appendNewSubtask(TaskDto dto) {
        return Optional.of(dto)
                .map(TaskDto::getTaskId)
                .flatMap(taskRepository::findById)
                .map(Task::withNewSubtask)
                .map(taskRepository::save)
                .map(Task::toDTO);
    }
    
    public Optional<TaskDto> createProject(TaskDto dto) {
        return Optional.of(dto)
                .map(Task::fromDto)
                .map(task -> {
                    task.setProject(true);
                    return task;
                })
                .map(task -> {
                    assert dto.getSubtaskIds() != null;
                    task.setSubTasks(
                            taskRepository.findAllById(dto.getSubtaskIds())
                    );
//                    em.persist(task);
                    return task;
                })
//                .map(em::save)
                .map(taskRepository::save)
                .map(Task::toDTO);
    }

}
