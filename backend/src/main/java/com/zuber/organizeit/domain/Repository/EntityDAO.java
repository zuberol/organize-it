package com.zuber.organizeit.domain.Repository;

import com.zuber.organizeit.domain.Note.Flashcard.Deck;
import com.zuber.organizeit.domain.Note.Flashcard.DeckTO;
import com.zuber.organizeit.domain.Note.Flashcard.Flashcard;
import com.zuber.organizeit.domain.Plan.PlanStatus;
import com.zuber.organizeit.domain.Plan.ShortTermPlan;
import com.zuber.organizeit.domain.Plan.ShortTermPlanDTO;
import com.zuber.organizeit.domain.Plan.ShortTermPlanRepository;
import com.zuber.organizeit.domain.Note.Snippet;
import com.zuber.organizeit.domain.Tag;
import com.zuber.organizeit.domain.Task.Task;
import com.zuber.organizeit.domain.Task.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

@Component
public class EntityDAO {

    private final TaskRepository taskRepository;
    private final EntityManager em;
    private final DecksRepository decksRepository;
    private final SnippetsRepository snippetsRepository;
    private final FlashcardsRepository flashcardsRepository;
    private final ShortTermPlanRepository shortTermPlanRepository;

    @Autowired
    public EntityDAO(TaskRepository taskRepository, EntityManager em, DecksRepository decksRepository, SnippetsRepository snippetsRepository, FlashcardsRepository flashcardsRepository, ShortTermPlanRepository shortTermPlanRepository) {
        this.taskRepository = taskRepository;
        this.em = em;
        this.decksRepository = decksRepository;
        this.snippetsRepository = snippetsRepository;
        this.flashcardsRepository = flashcardsRepository;
        this.shortTermPlanRepository = shortTermPlanRepository;
    }

    public Optional<Task> findTaskById(TaskDTO taskDTO) {
        return ofNullable(taskDTO)
                .map(TaskDTO::getId)
                .flatMap(id -> ofNullable(em.find(Task.class, id)));
//                .filter(Task::isNotArchived);
    }

    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Optional<ShortTermPlan> findPlanById(Long id) {
        return shortTermPlanRepository.findById(id);
    }

//    public Optional<Task> findByLocallySavedURI(String uri) {
//        return taskRepository.findTaskByLocallySavedURI(uri);
//    }

    public List<Task> findAllNonArchivedTasks() {
        return taskRepository.findAll()
                .stream()
//                .filter(Task::isNotArchived)
                .collect(Collectors.toList());
    }

    public Optional<Task> modifyTask(TaskDTO dto) {
        return of(dto)
                .flatMap(taskDTO -> ofNullable(taskDTO.getId()))
                .flatMap(taskRepository::findById)
                .map(task -> task.modifyBasicData(dto))
                .map(task -> task.setSubtasksFromTO(dto, taskRepository))
                .map(taskRepository::save);
    }

    public Optional<Task> appendNewSubtask(TaskDTO dto) {
        return of(dto)
                .map(TaskDTO::getId)
                .flatMap(taskRepository::findById)
                .map(task -> Task.withNewSubtask(task, em));
    }

    public Optional<Task> createTask(TaskDTO dto) {

        return of(dto)
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

    public List<Task> findTasks(List<Long> taskIds) {
        return taskRepository.findAllById(taskIds);
    }

    public List<Snippet> findByTag(String name) {
        return snippetsRepository.findByTag(name);
    }

    public List<Snippet> findByTags(String [] tags) {
        return stream(tags)
                .map(this::findByTag)
                .flatMap(List::stream)
                .toList();
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

    public List<ShortTermPlan> findAllPlans() {
        return shortTermPlanRepository.findAll();
    }

    public Optional<ShortTermPlan> findPlanByName(String name) {
        return shortTermPlanRepository.findPlanByName(name);
    }

    public Optional<ShortTermPlan> modifyPlan(ShortTermPlanDTO dto) {

        final var rootTasks = of(dto)
                        .map(ShortTermPlanDTO::getRootTaskIds)
                        .map(taskRepository::findAllById)
                        .orElse(null);

         return of(dto)
                 .map(ShortTermPlanDTO::getId)
                 .flatMap(shortTermPlanRepository::findById)
                 .map(plan -> plan.modifyBasicData(dto))
                 .map(plan -> {
                     ofNullable(rootTasks).ifPresent(plan::setRootTasks);
                    return plan;
                 })
                 .map(shortTermPlanRepository::save);
    }

    public ShortTermPlan createPlan(ShortTermPlanDTO dto) {
        Objects.requireNonNull(dto);
        var rootTasks = ofNullable(dto.getRootTaskIds())
                .map(taskRepository::findAllById)
                .orElseGet(LinkedList::new);
        return shortTermPlanRepository.save(
                ShortTermPlan.builder()
                        .name(dto.getName())
                        .description(dto.getDescription())
                        .rootTasks(rootTasks)
                        .status(PlanStatus.builder().build())
                        .build()
        );
    }


    public Deck createDeck(DeckTO deckTO) {
        Objects.requireNonNull(deckTO.getName());

        List<Flashcard> flashcards = ofNullable(deckTO.getFlashcardsIds())
                .map(flashcardsRepository::findAllById)
                .orElseGet(LinkedList::new);


        return decksRepository.save(
                Deck.builder()
                        .name(deckTO.getName())
                        .flashcards(flashcards).build()
        );
    }

    public ShortTermPlan save(ShortTermPlan plan) {
        return shortTermPlanRepository.save(plan);
    }

    public List<Snippet> findAllSnippets() {
        return snippetsRepository.findAll();
    }
}
