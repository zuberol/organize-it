package com.zuber.organizeit.Model.Task;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.zuber.organizeit.Model.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

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

    protected Task() {}

    public Task(Long id) {this.taskId = id;}

    public Task(String name) {
        this.name = name;
    }

    public Task(Long taskId, String name, String description, boolean isProject, boolean isDone, boolean isArchived, List<Task> subTasks, List<Tag> tags, TimeEstimates timeEstimates, String locallySavedURI) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.isProject = isProject;
        this.isDone = isDone;
        this.isArchived = isArchived;
        this.subTasks = subTasks;
        this.tags = tags;
        this.timeEstimates = timeEstimates;
        this.locallySavedURI = locallySavedURI;
    }

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("task_id")
    private Long taskId;

    @Builder.Default
    private String name = "";

    @Builder.Default
    private String description = "";

    @Builder.Default
    private boolean isProject = false;

    @Builder.Default
    private boolean isDone = false;

    @Builder.Default
    private boolean isArchived = false;


    @ManyToMany(
//            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
//            cascade = CascadeType.ALL,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
//            orphanRemoval = true // todo chyba ok
    )
    @Builder.Default
    @OrderBy("taskId DESC")
    private List<Task> subTasks = new LinkedList<>();

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Tag> tags = new LinkedList<>();

    @Embedded
    @Builder.Default
    private TimeEstimates timeEstimates = TimeEstimates.builder().build();

    @Column(unique = true) //todo
    private String locallySavedURI; //todo to Path

    public static Task fromDto(TaskTO taskTO) {
        return Task.builder().build().modify(taskTO);
    }

    public TaskTO toDTO() {
        return TaskTO.builder()
                .name(getName())
                .taskId(getTaskId())
                .description(getDescription())
                .subtaskIds(getSubTasks().stream().map(Task::getTaskId).collect(Collectors.toList()))
                .isDone(isDone())
                .build();
    }

    public Task modify(TaskTO taskTO) {
        Optional.ofNullable(taskTO.taskId).ifPresent(this::setTaskId);
        Optional.ofNullable(taskTO.name).ifPresent(this::setName);
        Optional.ofNullable(taskTO.isArchived).ifPresent(this::setArchived);
        Optional.ofNullable(taskTO.description).ifPresent(this::setDescription);
        Optional.ofNullable(taskTO.isDone).ifPresent(this::setDone);
        Optional.ofNullable(taskTO.subtaskIds)
                .map(Task.filterNulls).ifPresent(this::setSubTasks);
        return this;
    }


    public HashSet<TaskTO> nonArchivedTasks() {
        return nonArchivedTasks(new HashSet<>());
    }

    private HashSet<TaskTO> nonArchivedTasks(HashSet<TaskTO> tasks) {
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

//    public Task mergeNotNull(Task toMerge) {
////        Optional.ofNullable(toMerge.taskId).ifPresent(this::setTaskId);
//        Optional.ofNullable(toMerge.name).ifPresent(this::setName);
//        Optional.ofNullable(toMerge.description).ifPresent(this::setDescription);
//        Optional.ofNullable(toMerge.locallySavedURI).ifPresent(this::setLocallySavedURI);
//        Optional.ofNullable(toMerge.subTasks).ifPresent(this::setSubTasks);
//        Optional.of(toMerge.isArchived).ifPresent(this::setArchived);
//        Optional.of(toMerge.isDone).ifPresent(this::setDone);
//        Optional.of(toMerge.isProject).ifPresent(this::setProject);
//        return this;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isProject == task.isProject &&
                isDone == task.isDone &&
                isArchived == task.isArchived &&
                Objects.equals(taskId, task.taskId) &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(subTasks, task.subTasks) &&
                Objects.equals(tags, task.tags) &&
                Objects.equals(timeEstimates, task.timeEstimates) &&
                Objects.equals(locallySavedURI, task.locallySavedURI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, name, description, isProject, isDone, isArchived, subTasks, tags, timeEstimates, locallySavedURI);
    }


}


