package com.zuber.organizeit.Model.Note.Flashcard;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.zuber.organizeit.Model.Note.ReferenceResource.ReferenceResource;
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

    @ManyToMany(cascade = ALL) //todo chyba
    private List<ReferenceResource> questionPart;

    private String shortAnswer;

    @Column(columnDefinition="text", length=10000)
    private String longAnswer;

    @ManyToMany(cascade = ALL)
    private List<ReferenceResource> referenceResources;

    private boolean isArchived;

//    @OneToOne // todo for now
    @Embedded
    private Statistic statistic;

}
