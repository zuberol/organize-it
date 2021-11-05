package com.zuber.organizeit.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter
@Setter
@NoArgsConstructor
@Table(name = "flashcards")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="fc_id")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Flashcard implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fcId;

    private String question;
    private String shortAnswer;
    @Column(columnDefinition="text",length=10000)
    private String longAnswer;
    @OneToMany
//    @JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, property="@class")
    private List<ReferenceResource> referenceResources;
//    private Integer lameScore;
//    private String codeSample;

    public static boolean isValid(Flashcard flashcard) {
        return flashcard.question != null && flashcard.shortAnswer != null && flashcard.longAnswer != null;
    }

}
