package com.zuber.organizeit.Model.Note.ReferenceResource;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter @Setter
@Table(name = "reference_resources")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="res_id")
@Inheritance //todo Superbuilder
public abstract class ReferenceResource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    @JsonProperty("res_id")
    private Long id;

    private String caption;

    private String comment;

    private String referenceUrl;

    private String locallySavedURI;

}
