package com.zuber.organizeit.Model.Repository;

import com.zuber.organizeit.Model.Task.Task;
import com.zuber.organizeit.Model.Task.TaskTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
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

    public Task merge(Task task) {
        return em.merge(task);
    }

    public void delete(Task task) {
        taskRepository.delete(task);
    }

    public Optional<TaskTO> findById(TaskTO taskTO) {
        return ofNullable(taskTO)
                .map(TaskTO::getTaskId)
                .flatMap(id -> ofNullable(em.find(Task.class, id)))
                .filter(Task::isNotArchived)
                .map(Task::toDTO);
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public Optional<Task> findByLocallySavedURI(String uri) {
        return taskRepository.findTaskByLocallySavedURI(uri);
    }

    public List<TaskTO> findAllNonArchivedProjects() {
        return taskRepository.findAll()
                .stream()
                .filter(Task::isProject).filter(Task::isNotArchived)
                .map(Task::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<TaskTO> modifyTask(TaskTO dto) {
        return Optional.of(dto)
                .flatMap(taskTO -> ofNullable(taskTO.getTaskId()))
                .flatMap(taskRepository::findById)
                .map(task -> task.modifyBasicData(dto))
                .map(task -> task.setSubtasksFromTO(dto, taskRepository))
                .map(taskRepository::save)
                .map(Task::toDTO);
    }

    public Optional<TaskTO> appendNewSubtask(TaskTO dto) {
         return Optional.of(dto)
                .map(TaskTO::getTaskId)
                .flatMap(taskRepository::findById)
                .map(task -> Task.withNewSubtask(task, em))
                .map(Task::toDTO);
    }
    
    public Optional<TaskTO> createProject(TaskTO dto) {

        return Optional.of(dto)
                .map(Task::newFromDto)
                .map(task -> task.setSubtasksFromTO(dto, taskRepository))
                .map(taskRepository::save)
                .map(Task::toDTO);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void persist(Task task) {
        em.persist(task);
    }

    // czy mozna tu przekazac Optionala? czy jest iterable?
    // nie mozna, ale Option ze scali juz tak chyba
    public List<Task> saveAll(Iterable<Task> tasks) {
        return taskRepository.saveAll(tasks);
    }
}
