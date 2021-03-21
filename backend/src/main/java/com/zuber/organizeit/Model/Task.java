package com.zuber.organizeit.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Getter @Setter @NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
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
    Long task_id;

    @ManyToOne(fetch= FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    Task parentTask;

    @OneToMany(fetch= FetchType.LAZY, mappedBy = "parentTask")
    @JsonManagedReference
    List<Task> subTasks;

    String note;

}
