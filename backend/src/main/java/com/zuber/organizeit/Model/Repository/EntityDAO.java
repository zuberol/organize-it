package com.zuber.organizeit.Model.Repository;

import com.zuber.organizeit.Model.Note.Flashcard.Deck;
import com.zuber.organizeit.Model.Note.Flashcard.Flashcard;
import com.zuber.organizeit.Model.Snippet;
import com.zuber.organizeit.Model.Tag;
import com.zuber.organizeit.Model.Task.Task;
import com.zuber.organizeit.Model.Task.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;

@Component
public class EntityDAO {

    final TaskRepository taskRepository;
    final EntityManager em;
    final DecksRepository decksRepository;
    final SnippetsRepository snippetsRepository;
    final FlashcardsRepository flashcardsRepository;

    @Autowired
    public EntityDAO(TaskRepository taskRepository, EntityManager em, DecksRepository decksRepository, SnippetsRepository snippetsRepository, FlashcardsRepository flashcardsRepository) {
        this.taskRepository = taskRepository;
        this.em = em;
        this.decksRepository = decksRepository;
        this.snippetsRepository = snippetsRepository;
        this.flashcardsRepository = flashcardsRepository;
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

    public Optional<Task> appendSubtask(TaskDTO dto, Long parentTaskId) {
        Optional<Task> subTaskOpt = createTask(dto);
        Optional<Task> parentOpt = taskRepository.findById(parentTaskId);
        Task toReturn = null;
        if (subTaskOpt.isPresent() && parentOpt.isPresent()) {
            Task parent = parentOpt.get();
            Task subTask = subTaskOpt.get();
            parent.getSubTasks().add(subTask);
            taskRepository.save(parent);
            toReturn = subTask;
        }
        return ofNullable(toReturn);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Deck save(Deck deck) {
        return decksRepository.save(deck);
    }

    public Snippet save(Snippet snippet) {
        return snippetsRepository.save(snippet);
    }

    public List<Task> getInboxTasks() {
        return taskRepository.isNotSubtaskAndIsNotProject();
    }

    public List<Snippet> findByTag(String name) {
        return snippetsRepository.findByTag(name);
    }

    public List<Flashcard> getRandomFlashcards(String[] tags) {
        Flashcard probe = new Flashcard();
        probe.setTags(stream(tags).map(Tag::new).toList());
//        Example<Flashcard> example = Example.of(probe, ExampleMatcher.matchingAny()
//                .withMatcher("tags.mainName", match -> {
//            match.transform(source -> ofNullable(source.toString()));
//            match.contains();
//        }));

        return flashcardsRepository.getRandomFlashcards(stream(tags).toList());
    }
}
