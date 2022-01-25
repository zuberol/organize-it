package com.zuber.organizeit.domain.Task;

import com.zuber.organizeit.domain.BaseAggregateRoot;
import com.zuber.organizeit.domain.Repository.TaskRepository;
import com.zuber.organizeit.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.zuber.organizeit.utils.Utils.not;
import static java.util.Optional.ofNullable;
import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.PERSIST;

@Entity
//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter @Setter @AllArgsConstructor
@Builder
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "task_id")
@Table(name = "tasks")
public class Task extends BaseAggregateRoot<Long> implements Serializable {

    protected Task() {}

    public Task(Long id) {this.setId(id);}

    public Task(String name) {
        this.name = name;
    }

    @Builder.Default
    private String name = "";

    @Builder.Default
    @Column(length = 10000)
    private String description = "";

    @ManyToMany(cascade = {ALL, REMOVE}) // todo tu appendTo nie wiem?
    @Builder.Default
//    @OrderBy("priority DESC")
    private List<Task> subTasks = new LinkedList<>();

    @ManyToMany(cascade = {PERSIST})
    @Builder.Default
    @JoinTable(joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "tag_id") )
    private List<Tag> tags = new LinkedList<>();

    public static Task newFromDto(TaskTO taskTO) {
        return Task.builder().build().modifyBasicData(taskTO);
    }

    public Task modifyBasicData(TaskTO taskTO) {
        ofNullable(taskTO.id).ifPresent(this::setId);
        ofNullable(taskTO.name).ifPresent(this::setName);
        ofNullable(taskTO.isArchived).ifPresent(archived -> {
            if(this.getStatus() == null) this.setStatus(TaskStatus.builder().build());
            if(archived) {
                this.getStatus().setArchived(new Archived(LocalDateTime.now(), true));
            } else this.getStatus().setArchived(new Archived(null, false));
        });
        ofNullable(taskTO.isDone).ifPresent(done -> {
            if(this.getStatus() == null) this.setStatus(TaskStatus.builder().build());
            if(done) {
                this.getStatus().setDone(new Done(LocalDateTime.now(), true));
            } else this.getStatus().setDone(new Done(null, false));
        });
        ofNullable(taskTO.description).ifPresent(this::setDescription);
//        ofNullable(taskDTO.priority).ifPresent(this::setPriority);

//        Optional.ofNullable(taskTO.subtaskIds)
//                .map(Task.filterNulls).ifPresent(this::setSubTasks);
        return this;
    }

    public Task setSubtasksFromTO(TaskTO taskTO, TaskRepository repo) {
        ofNullable(taskTO)
                .map(TaskTO::getSubtaskIds)
                .map(repo::findAllById)
                .ifPresent(this::setSubTasks);
        return this;
    }

    public TaskTO toDTO() {
        return TaskTO.builder()
                .name(getName())
                .id(getId())
                .description(getDescription())
                .subtaskIds(getSubTasks().stream().map(Task::getId).collect(Collectors.toList()))
//                .priority(getPriority())
//                .isDone(isDone())
                .build();
    }


//    public HashSet<TaskDTO> nonArchivedTasks() {
//        return nonArchivedTasks(new HashSet<>());
//    }

//    private HashSet<TaskDTO> nonArchivedTasks(HashSet<TaskDTO> tasks) {
//        if(isNotArchived()){
//            tasks.add(toDTO());
//            subTasks.forEach(subtask -> nonArchivedTasks(tasks));
//        }
//        return tasks;
//    }

//    public boolean isNotDone() {
//        return !isDone();
//    }
//
//    public boolean isNotArchived() {
//        return !isArchived();
//    }

    public static Task withNewSubtask(Task task, EntityManager em) {
        task.getSubTasks().add(Task.builder().build());
        em.persist(task);
        return task;
    }

    public Task appendSubtask(Task task) {
        if(getSubTasks() == null) setSubTasks(new LinkedList<>());
        boolean alreadyPresent = getSubTasks().stream().anyMatch(subTask -> Objects.equals(subTask.getId(), task.getId()));
        if(not(alreadyPresent)) getSubTasks().add(task);
        return this;
    }

    public Task removeSubtask(Task task) {
        if(getSubTasks() == null) setSubTasks(new LinkedList<>());
        setSubTasks(
                getSubTasks().stream()
                        .filter(subTask -> !Objects.equals(subTask.getId(), task.getId()))
                        .toList());
        return this;
    }

    private static final Function<List<Long>, List<Task>> filterNulls = list ->
            list.stream()
                    .filter(Objects::nonNull)
                    .map(Task::new)
                    .collect(Collectors.toList());


    @Embedded
    @Builder.Default
    TaskStatus status = TaskStatus.builder().build();

    @Embedded
    Difficulty difficulty;


//    @Column(name = "priority")
//    @Builder.Default
//    private Long priority = 1000L;
//
//    @Column(unique = true) //todo
//    private String locallySavedURI; //todo appendTo Path
//
//    @Builder.Default
//    private boolean isDone = false;
//
//    @Builder.Default
//    private boolean isArchived = false;


//    @Embedded
//    @Builder.Default
//    private TimeEstimates timeEstimates = TimeEstimates.builder().build();


}


