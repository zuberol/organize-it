package com.zuber.organizeit.Model.Flashcard;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.zuber.organizeit.Model.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@Getter @Setter @NoArgsConstructor
@Table(
    name = "decks",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"title"}, name = "uniqueTitleConstraint")}
)
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deckId;

    private String title;

    @OneToMany
    private List<Flashcard> flashcards;

}
