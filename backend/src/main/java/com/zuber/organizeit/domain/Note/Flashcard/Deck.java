package com.zuber.organizeit.domain.Note.Flashcard;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.zuber.organizeit.domain.BaseEntity;
import com.zuber.organizeit.domain.Tag;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@Getter @Setter @NoArgsConstructor
@Builder @AllArgsConstructor
@Table(
    name = "decks",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "uniqueTitleConstraint")}
)
public class Deck extends BaseEntity<Long> {

    private String name;

    @OneToMany(cascade = ALL)
    private List<Flashcard> flashcards;

    @ManyToMany(cascade = {PERSIST})
    @Builder.Default
    @JoinTable(joinColumns = @JoinColumn(name = "did"), inverseJoinColumns = @JoinColumn(name = "tid") )
    private List<Tag> tags = new LinkedList<>();

}
