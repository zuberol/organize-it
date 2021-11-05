package com.zuber.organizeit.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter @Setter @NoArgsConstructor
@Builder
@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "task_id")
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "task_id")
    @JsonProperty("task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
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

    @OneToMany(
            cascade = {CascadeType.ALL}
    )
    @Builder.Default
    List<Tag> tags = new LinkedList<>();

    @Embedded
    @Builder.Default
    TimeEstimates timeEstimates = TimeEstimates.builder().build();


}


