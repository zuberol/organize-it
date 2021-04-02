package com.zuber.organizeit.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

// ogarnac jaki plik uzyc do constrainsow  w hibernate
// constrainsy ogarnac na bazie
//ogarnac zeby juz baza byla stabilna
//todo wrzucic dane do bazy


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "flashcards")
public class Flashcard implements Serializable {

    @Id
    @SequenceGenerator(
            name = "flashcard_seq",
            sequenceName = "flashcard_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy=SEQUENCE,
            generator="flashcard_seq"
    )
    private Long fcId;
    private String question;
    private String shortAnswer;
    private String longAnswer;

    @OneToMany
    private List<ReferenceSource> referenceSources;
    private String pictureUrl;
    private Integer lameScore;
    private String codeSample;

    public static boolean isValid(Flashcard flashcard) {
        return flashcard.question != null && flashcard.shortAnswer != null && flashcard.longAnswer != null;
    }

}
