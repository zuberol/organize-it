package com.zuber.organizeit.Model.Flashcard;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter @Setter @NoArgsConstructor
@DiscriminatorValue("5")
public class CodeReference extends ReferenceResource {

    public CodeReference(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    @Column(length = 10000)
    private String sourceCode;

}
