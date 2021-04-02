package com.zuber.organizeit.Model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Getter @Setter @NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "taskId")
public class Task {

    @Id
    @SequenceGenerator(
            name = "task_seq",
            sequenceName = "task_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy=SEQUENCE,
            generator="task_seq"
    )
    Long taskId;
    String note;

    @ManyToOne(
//            fetch= FetchType.LAZY,
            cascade={CascadeType.PERSIST,
                    CascadeType.MERGE}
    )
    @JsonIdentityReference(alwaysAsId = true)
    Task parentTask;

    @JsonProperty("parentTask")
    public void setFoo(Long id) {
        parentTask = Task.builder().taskId(id).build();     //https://stackoverflow.com/questions/18306040/jackson-deserialize-jsonidentityreference-alwaysasid-true/29742035#29742035
    }

    @OneToMany(
//            fetch= FetchType.LAZY,
            mappedBy = "parentTask"
    )
    List<Task> subTasks;

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", note='" + note + '\'' +
                ", parentTask=" + parentTask +
                ", subTasks=" + subTasks +
                '}';
    }
}
