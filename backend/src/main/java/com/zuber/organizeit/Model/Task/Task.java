package com.zuber.organizeit.Model.Task;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.zuber.organizeit.Model.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter @Setter
@Builder
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "task_id")
@Table(name = "tasks")
public class Task {

    public Task() {}

    public Task(Long id) {this.taskId = id;}

    public Task(String name) {
        this.name = name;
    }

    public Task(Long taskId, String name, String description, boolean isProject, boolean isDone, boolean isArchived, List<Task> subTasks, List<Tag> tags, TimeEstimates timeEstimates) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.isProject = isProject;
        this.isDone = isDone;
        this.isArchived = isArchived;
        this.subTasks = subTasks;
        this.tags = tags;
        this.timeEstimates = timeEstimates;
    }

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("task_id")
    Long taskId;

    @Builder.Default
    String name = "";

    @Builder.Default
    String description = "";

    @Builder.Default
    boolean isProject = false;

    @Builder.Default
    boolean isDone = false;

    @Builder.Default
    boolean isArchived = false;


    @ManyToMany(
//            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            cascade = CascadeType.ALL//,
//            orphanRemoval = true // todo chyba ok
    )
    @Builder.Default
    @OrderBy("taskId DESC")
    List<Task> subTasks = new LinkedList<>();

    @OneToMany(cascade = {CascadeType.ALL})
    List<Tag> tags = new LinkedList<>();

    @Embedded
    @Builder.Default
    TimeEstimates timeEstimates = TimeEstimates.builder().build();


    public static Task fromDto(TaskDto taskDto) {
        return Task.builder().build().modify(taskDto);
    }

    public TaskDto toDTO() {
        return TaskDto.builder()
                .taskId(getTaskId())
                .description(getDescription())
                .subtaskIds(getSubTasks().stream().map(Task::getTaskId).collect(Collectors.toList()))
                .isDone(isDone())
                .build();
    }

    public Task modify(TaskDto taskDTO) {
        Optional.ofNullable(taskDTO.taskId).ifPresent(this::setTaskId);
        Optional.ofNullable(taskDTO.name).ifPresent(this::setName);
        Optional.ofNullable(taskDTO.isArchived).ifPresent(this::setArchived);
        Optional.ofNullable(taskDTO.description).ifPresent(this::setDescription);
        Optional.ofNullable(taskDTO.isDone).ifPresent(this::setDone);
        Optional.ofNullable(taskDTO.subtaskIds)
                .map(Task.filterNulls).ifPresent(this::setSubTasks);
        return this;
    }


    public HashSet<TaskDto> nonArchivedTasks() {
        return nonArchivedTasks(new HashSet<>());
    }

    private HashSet<TaskDto> nonArchivedTasks(HashSet<TaskDto> tasks) {
        if(isNotArchived()){
            tasks.add(toDTO());
            subTasks.forEach(subtask -> nonArchivedTasks(tasks));
        }
        return tasks;
    }

    public boolean isNotDone() {
        return !isDone();
    }

    public boolean isNotArchived() {
        return !isArchived();
    }

    public static Task withNewSubtask(Task task) {
        task.getSubTasks().add(Task.builder().build());
        return task;
    }

    private static final Function<List<Long>, List<Task>> filterNulls = list ->
            list.stream()
                    .filter(Objects::nonNull)
                    .map(Task::new)
                    .collect(Collectors.toList());
}


