package com.zuber.organizeit.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "flashcarddecks")
public class FlashcardDeck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long deckId;
    private String title;
    @OneToOne
    private User creator;


    @OneToMany
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="fcId")
    private List<Flashcard> flashcards;

    public static boolean isValid(FlashcardDeck flashcardDeck) {
        if(flashcardDeck == null) return false;
        return flashcardDeck.deckId != null && flashcardDeck.title != null && flashcardDeck.flashcards != null;
    }
}
