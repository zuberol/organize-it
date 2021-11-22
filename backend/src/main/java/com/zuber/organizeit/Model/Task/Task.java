package com.zuber.organizeit.Model.Task;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.zuber.organizeit.Model.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter @Setter
@Builder
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "task_id")
@Table(name = "tasks")
public class Task {

    public Task() {}

    public Task(String name) {
        this.name = name;
    }

    public Task(Long taskId, String name, String description, boolean isRoot, boolean isDone, boolean isArchived, List<Task> subTasks, List<Tag> tags, TimeEstimates timeEstimates) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.isRoot = isRoot;
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
    boolean isRoot = false;

    @Builder.Default
    boolean isDone = false;

    @Builder.Default
    boolean isArchived = false;


    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    @Builder.Default
    @OrderBy("taskId DESC")
    List<Task> subTasks = new LinkedList<>();

    @OneToMany(cascade = {CascadeType.ALL})
    List<Tag> tags = new LinkedList<>();

    @Embedded
    @Builder.Default
    TimeEstimates timeEstimates = TimeEstimates.builder().build();


//    @Autowired
//    StructureManipulator structureManipulator;



    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", subTasks=" + subTasks +
                '}';
    }
}


