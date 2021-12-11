package com.zuber.organizeit.Model.Flashcard;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter @Setter @NoArgsConstructor
@Table(name = "flashcards")
public class Flashcard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fcId;

    private String question;

    private String shortAnswer;

    @Column(columnDefinition="text", length=10000)
    private String longAnswer;

    @OneToMany(cascade = ALL)
    private List<ReferenceResource> referenceResources;

}
