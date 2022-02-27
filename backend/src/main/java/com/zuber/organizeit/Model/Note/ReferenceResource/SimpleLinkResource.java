package com.zuber.organizeit.Model.Note.ReferenceResource;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("1")
public class SimpleLinkResource extends ReferenceResource {

    public SimpleLinkResource(String link) {
        setReferenceUrl(link);
    }

    @Override
    public String toString() {
        return "SimpleLinkResource{" +
                "referenceUrl='" + referenceUrl + '\'' +
                '}';
    }
}