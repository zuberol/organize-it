package com.zuber.organizeit.Model.Flashcard;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter @Setter @NoArgsConstructor
@DiscriminatorValue("3")
public class ImageReference extends ReferenceResource {

    private String referenceUrl;

    private String imageUri;

}
