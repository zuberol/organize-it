package com.zuber.organizeit.Model.Flashcard;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter @Setter @NoArgsConstructor
@DiscriminatorValue("5")
//@SuperBuilder //todo Superbuilder
public class CodeReference extends ReferenceResource {

    // for lombok
    private CodeReference(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    @Column(length = 10000)
    private String sourceCode;

}
