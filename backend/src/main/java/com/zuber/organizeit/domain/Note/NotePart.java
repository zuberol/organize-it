package com.zuber.organizeit.domain.Note;


import com.zuber.organizeit.domain.BaseEntity;
import com.zuber.organizeit.domain.Note.Flashcard.Flashcard;
import com.zuber.organizeit.domain.ReferenceResource.ReferenceResource;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@AllArgsConstructor @Builder @NoArgsConstructor
@Getter @Setter
public class NotePart extends BaseEntity<Long> {

    @Builder.Default
    private String text = "";

    @OneToMany(cascade = ALL)
    @Builder.Default
    private List<NotePart> subParts = new ArrayList<>();

    @OneToMany(cascade = ALL)
    @Builder.Default
    private List<ReferenceResource> referenceResources = new ArrayList<>();

    @OneToMany
    @Builder.Default
    private List<Flashcard> flashcards = new ArrayList<>();

}
