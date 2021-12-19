package com.zuber.organizeit.Model.Note;


import com.zuber.organizeit.Model.Note.ReferenceResource.ReferenceResource;
import com.zuber.organizeit.Model.Tag;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "notes")
@Getter @Setter @NoArgsConstructor
@Builder @AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "note_id")
    private Long id;

    private String name;
    private String content;

    @ManyToMany(cascade = ALL)
    List<Tag> tags;

    @OneToMany(cascade = ALL)
    List<ReferenceResource> referenceResources;



}
