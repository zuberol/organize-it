package com.zuber.organizeit.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
@Getter
@Setter
@NoArgsConstructor
@Table(name = "flashcards")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="fc_id")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Flashcard implements Serializable {

    public final static String ID_SEQ_NAME = "flashcard_seq";

    @Id
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
