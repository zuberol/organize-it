package com.zuber.organizeit.domain.Note;


import com.zuber.organizeit.domain.BaseEntity;
import com.zuber.organizeit.domain.ReferenceResource.ReferenceResource;
import com.zuber.organizeit.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter @Setter
@Table(name = "snippets")
@Builder @AllArgsConstructor
public class Snippet extends BaseEntity<Long> {

    protected Snippet() {}

    private String name;
    @Column(length = 10000)
    private String content;

    @ManyToMany(cascade = {PERSIST, MERGE})
    @JoinTable(joinColumns = @JoinColumn(name = "sid"), inverseJoinColumns = @JoinColumn(name = "tid") )
    @Builder.Default
    List<Tag> tags = new LinkedList<>();

    @OneToMany
    @Builder.Default
    List<ReferenceResource> referenceResources = new LinkedList<>();
}
