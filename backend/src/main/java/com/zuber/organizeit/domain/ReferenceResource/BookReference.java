package com.zuber.organizeit.domain.ReferenceResource;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter @Setter @NoArgsConstructor
@DiscriminatorValue("4")
public class BookReference extends ReferenceResource {

    private String title;

    private String author;

    private String pageNumber;

}
