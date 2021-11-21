package com.zuber.organizeit.Model;


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
@DiscriminatorValue("5")
public class CodeReference extends ReferenceResource {
    String sourceCode;

    public CodeReference(String sourceCode) {
        this.sourceCode = sourceCode;
    }
}
