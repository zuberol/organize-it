package com.zuber.organizeit.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
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
    Long taskId;

    @Builder.Default
    String note = "";

    @Builder.Default
    boolean isProject = false;

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

}
