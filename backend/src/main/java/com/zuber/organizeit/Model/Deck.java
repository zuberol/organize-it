package com.zuber.organizeit.Model;

import com.zuber.organizeit.Model.tests.ExternalEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(
        name = "decks",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"title"},
                        name = "uniqueTitleConstraint"
                )
        }
)
public class Deck implements ExternalEntity {

    public final static String ID_SEQ_NAME = "deck_seq";

    @Id
    Long deckId;



    private String title;
    @OneToOne
    private User creator;


//    @OneToMany
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="fc_id")
////    @JsonBackReference //todo nie wiadomo ktore bylo dobrze
//    private List<Flashcard> flashcards;
    @OneToMany
//    @JsonBackReference //todo nie wiadomo ktore bylo dobrze
//    @JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, property="@chuj")
    private List<Flashcard> flashcards;



    public static boolean isValid(Deck deck) {
        if(deck == null) return false;
        return deck.deckId != null && deck.title != null && deck.flashcards != null;
    }

}
