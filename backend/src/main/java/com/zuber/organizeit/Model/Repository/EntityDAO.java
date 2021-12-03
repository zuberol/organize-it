package com.zuber.organizeit.Model.Repository;

import com.zuber.organizeit.Model.Task.Task;
import com.zuber.organizeit.Model.Task.TaskDTO;
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

    public Task merge(Task task) {
        return em.merge(task);
    }

    public void delete(Task task) {
        taskRepository.delete(task);
    }

    public Optional<Task> findById(TaskDTO taskDTO) {
        return ofNullable(taskDTO)
                .map(TaskDTO::getTaskId)
                .flatMap(id -> ofNullable(em.find(Task.class, id)))
                .filter(Task::isNotArchived);
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public Optional<Task> findByLocallySavedURI(String uri) {
        return taskRepository.findTaskByLocallySavedURI(uri);
    }

    public List<Task> findAllNonArchivedProjects() {
        return taskRepository.findAll()
                .stream()
                .filter(Task::isProject).filter(Task::isNotArchived)
                .collect(Collectors.toList());
    }

    public Optional<Task> modifyTask(TaskDTO dto) {
        return Optional.of(dto)
                .flatMap(taskDTO -> ofNullable(taskDTO.getTaskId()))
                .flatMap(taskRepository::findById)
                .map(task -> task.modifyBasicData(dto))
                .map(task -> task.setSubtasksFromTO(dto, taskRepository))
                .map(taskRepository::save);
    }

    public Optional<Task> appendNewSubtask(TaskDTO dto) {
         return Optional.of(dto)
                .map(TaskDTO::getTaskId)
                .flatMap(taskRepository::findById)
                .map(task -> Task.withNewSubtask(task, em));
    }
    
    public Optional<Task> createTask(TaskDTO dto) {

        return Optional.of(dto)
                .map(Task::newFromDto)
                .map(task -> task.setSubtasksFromTO(dto, taskRepository))
                .map(taskRepository::save);
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

    public List<Task> getInboxTasks() {
        return taskRepository.isNotSubtaskAndIsNotProject();
    }


}
