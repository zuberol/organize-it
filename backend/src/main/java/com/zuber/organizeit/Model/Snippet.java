package com.zuber.organizeit.Model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static javax.persistence.GenerationType.*;


//todo snippets feature, like code or smth, idea = quick recap
@Entity
@Getter @Setter
public class Snippet {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

}
