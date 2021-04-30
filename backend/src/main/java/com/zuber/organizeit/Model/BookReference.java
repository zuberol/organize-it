package com.zuber.organizeit.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter
@NoArgsConstructor
public class BookReference extends ReferenceResource {
    private String title;
    private String author;
    private String page;
}
