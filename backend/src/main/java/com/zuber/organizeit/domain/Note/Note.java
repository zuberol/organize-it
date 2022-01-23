package com.zuber.organizeit.domain.Note;


import com.zuber.organizeit.domain.BaseAggregateRoot;
import com.zuber.organizeit.domain.Note.Flashcard.Flashcard;
import com.zuber.organizeit.domain.ReferenceResource.ReferenceResource;
import com.zuber.organizeit.domain.Tag;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "notes")
@Getter @Setter @NoArgsConstructor
@Builder @AllArgsConstructor
public class Note extends BaseAggregateRoot<Long> {

    private String name;
    private String content;

    @OneToMany
    @Builder.Default
    private List<NotePart> noteParts = new LinkedList<>();

    @OneToMany(cascade = ALL)
    @Builder.Default
    private List<Snippet> snippets = new LinkedList<>();

    @OneToMany(cascade = ALL)
    @Builder.Default
    private List<ReferenceResource> referenceResources = new LinkedList<>();

    @OneToMany
    @Builder.Default
    private List<Flashcard> flashcards = new LinkedList<>();

    @ManyToMany(cascade = {PERSIST})
    @Builder.Default
    private List<Tag> tags = new LinkedList<>();

}
