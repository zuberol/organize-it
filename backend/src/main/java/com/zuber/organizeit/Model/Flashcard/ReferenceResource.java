package com.zuber.organizeit.Model.Flashcard;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.net.URI;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter @Setter
@Table(name = "reference_resources")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="res_id")
@Inheritance
public abstract class ReferenceResource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    @JsonProperty("res_id")
    private Long id;

    private String caption;

    private String comment;

    private String referenceUrl;

}
