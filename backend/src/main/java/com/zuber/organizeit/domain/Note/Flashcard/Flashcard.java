package com.zuber.organizeit.domain.Note.Flashcard;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.zuber.organizeit.domain.BaseEntity;
import com.zuber.organizeit.domain.ReferenceResource.ReferenceResource;
import com.zuber.organizeit.domain.Tag;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter @Setter @NoArgsConstructor
@Table(name = "flashcards")
@Builder @AllArgsConstructor
public class Flashcard extends BaseEntity<Long> {

    private String question;

    @OneToMany(cascade = ALL)
    private List<ReferenceResource> questionPart;

    private String shortAnswer;

    @Column(columnDefinition="text", length=10000)
    private String longAnswer;

    @ManyToMany(cascade = ALL)
    private List<ReferenceResource> referenceResources;

    private boolean isArchived;

    @Embedded
    private Statistic statistic;

    @ManyToMany(cascade = ALL)
//    @JoinColumn(name = "fc_id")
    private List<Tag> tags;

}
