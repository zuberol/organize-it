package com.zuber.organizeit.domain.Note;


import com.zuber.organizeit.domain.BaseEntity;
import com.zuber.organizeit.domain.Tag;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter @Setter
@Table(name = "snippets")
public class Snippet extends BaseEntity<Long> {

    private String name;
    @Column(length = 10000)
    private String content;

    @ManyToMany(cascade = ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "sid"), inverseJoinColumns = @JoinColumn(name = "tid") )
    List<Tag> tags;

}
