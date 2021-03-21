package com.zuber.organizeit.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor
public class Flashcard {

    @Id
    private Long id;



}
