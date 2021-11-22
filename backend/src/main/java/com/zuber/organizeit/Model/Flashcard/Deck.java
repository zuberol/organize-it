package com.zuber.organizeit.Model.Flashcard;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.zuber.organizeit.Model.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
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
public class Deck {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long deckId;



    private String title;
    @OneToOne
    private User creator;

    private String someProp;

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
