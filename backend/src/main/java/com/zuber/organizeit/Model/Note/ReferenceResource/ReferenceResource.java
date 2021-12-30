package com.zuber.organizeit.Model.Note.ReferenceResource;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter @Setter
@Table(name = "reference_resources")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="resId")
@Inheritance //todo Superbuilder
public abstract class ReferenceResource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    private Long resId;

    private String caption;

    private String comment;

    protected String referenceUrl; // private vs protected

    private String locallySavedURI;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReferenceResource that = (ReferenceResource) o;
        return Objects.equals(resId, that.resId) && Objects.equals(caption, that.caption) && Objects.equals(comment, that.comment) && Objects.equals(referenceUrl, that.referenceUrl) && Objects.equals(locallySavedURI, that.locallySavedURI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resId, caption, comment, referenceUrl, locallySavedURI);
    }
}
