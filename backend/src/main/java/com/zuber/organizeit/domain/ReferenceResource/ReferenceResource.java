package com.zuber.organizeit.domain.ReferenceResource;

import com.fasterxml.jackson.annotation.*;
import com.zuber.organizeit.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity @Inheritance @Table(name = "reference_resources")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter // todo chyba nie trzeba settera
public abstract class ReferenceResource extends BaseEntity<Long> {

    private String caption;

    private String comment;

    protected String referenceUrl; // private vs protected

    private String locallySavedURI;

}
