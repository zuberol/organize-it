package com.zuber.organizeit.domain.ReferenceResource;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("1")
public class SimpleLinkResource extends ReferenceResource {

    @Builder
    public SimpleLinkResource(String caption, String comment, String referenceUrl, String locallySavedURI) {
        super(caption, comment, referenceUrl, locallySavedURI);
    }

}
