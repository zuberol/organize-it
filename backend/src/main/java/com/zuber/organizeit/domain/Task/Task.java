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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @ManyToMany(cascade = {ALL, REMOVE}) // todo tu to nie wiem?
    @Builder.Default
//    @OrderBy("priority DESC")
    private List<Task> subTasks = new LinkedList<>();

    @ManyToMany(cascade = {PERSIST})
    @Builder.Default
    @JoinTable(joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "tag_id") )
    private List<Tag> tags = new LinkedList<>();

    public static Task newFromDto(TaskDTO taskDTO) {
        return Task.builder().build().modifyBasicData(taskDTO);
    }

    public Task modifyBasicData(TaskDTO taskDTO) {
        ofNullable(taskDTO.id).ifPresent(this::setId);
        ofNullable(taskDTO.name).ifPresent(this::setName);
//        ofNullable(taskDTO.isArchived).ifPresent(this::setArchived);
        ofNullable(taskDTO.description).ifPresent(this::setDescription);
//        ofNullable(taskDTO.isDone).ifPresent(this::setDone);
//        ofNullable(taskDTO.priority).ifPresent(this::setPriority);

//        Optional.ofNullable(taskTO.subtaskIds)
//                .map(Task.filterNulls).ifPresent(this::setSubTasks);
        return this;
    }

    public Task setSubtasksFromTO(TaskDTO taskDTO, TaskRepository repo) {
        ofNullable(taskDTO)
                .map(TaskDTO::getSubtaskIds)
                .map(repo::findAllById)
                .ifPresent(this::setSubTasks);
        return this;
    }

    public TaskDTO toDTO() {
        return TaskDTO.builder()
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
//    private String locallySavedURI; //todo to Path
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


