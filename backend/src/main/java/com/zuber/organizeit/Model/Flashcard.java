package com.zuber.organizeit.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private String longAnswer;
    @OneToMany
//    @JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, property="@class")
    private List<ReferenceResource> referenceResources;
//    private String pictureUrl;
//    private Integer lameScore;
//    private String codeSample;

    public static boolean isValid(Flashcard flashcard) {
        return flashcard.question != null && flashcard.shortAnswer != null && flashcard.longAnswer != null;
    }

}
