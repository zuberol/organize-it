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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "task_id") //todo chyba musi to byc, ale do sprawdzenia czy dziala deserializacja
@Table(name = "tasks")
public class Task {

    public final static String ID_SEQ_NAME = "task_seq";


    @Id
    Long taskId;
    String note = "";
    boolean isProject = false;

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
                ", isProject=" + isProject +
                ", parentTask=" + parentTask +
                ", subTasks=" + subTasks +
                '}';
    }
}
