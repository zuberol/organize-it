package com.zuber.organizeit.Model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter @Setter
@NoArgsConstructor
public class BookReference extends ReferenceResource {
    private String title;
    private String author;
    private String page;
}
