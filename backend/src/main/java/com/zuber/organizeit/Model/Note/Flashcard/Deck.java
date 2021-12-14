package com.zuber.organizeit.Model.Note.Flashcard;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.zuber.organizeit.Model.Note.Flashcard.Flashcard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@Getter @Setter @NoArgsConstructor
@Table(
    name = "decks",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"title"}, name = "uniqueTitleConstraint")}
)
public class Deck {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long deckId;

    private String title;

    @OneToMany(cascade = ALL)
    private List<Flashcard> flashcards;

}
