package com.zuber.organizeit.domain.Note;


import com.zuber.organizeit.domain.BaseAggregateRoot;
import com.zuber.organizeit.domain.Note.Flashcard.Flashcard;
import com.zuber.organizeit.domain.ReferenceResource.ReferenceResource;
import com.zuber.organizeit.domain.Tag;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "notes")
@Getter @Setter @NoArgsConstructor
@Builder @AllArgsConstructor
public class Note extends BaseAggregateRoot<Long> {

    private String name;
    private String content;

    @ManyToMany
    private List<Tag> tags;

    @OneToMany(cascade = ALL)
    @Builder.Default
    private List<ReferenceResource> referenceResources = new ArrayList<>();

    @ManyToMany
    @Builder.Default
    private List<Flashcard> flashcards = new ArrayList<>();

    @OneToMany
    @Builder.Default
    private List<NotePart> noteParts = new ArrayList<>();


}
