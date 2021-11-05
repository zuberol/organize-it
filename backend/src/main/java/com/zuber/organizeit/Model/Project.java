package com.zuber.organizeit.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
    @JsonProperty("project_id")
    private Long id;

    String name;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @Builder.Default
    Task rootTask = Task.builder().description("What's poppin?").build();

    String description;

    @OneToMany(cascade = {CascadeType.ALL})
    List<Tag> tags;


}
