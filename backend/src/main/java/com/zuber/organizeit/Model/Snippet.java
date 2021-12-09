package com.zuber.organizeit.Model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.*;


//todo snippets feature, like code or smth, idea = quick recap
@Entity
@Getter @Setter
@Table(name = "snippets")
public class Snippet {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "snippet_id")
    private Long id;

    private String title;
    @Column(length = 10000)
    private String content;

    @ManyToMany(cascade = ALL)
    List<Tag> tags;

}
