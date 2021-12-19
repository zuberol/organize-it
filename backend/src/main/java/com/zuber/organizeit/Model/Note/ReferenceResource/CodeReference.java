package com.zuber.organizeit.Model.Note.ReferenceResource;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.zuber.organizeit.Model.Note.ReferenceResource.ReferenceResource;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CodeReference that = (CodeReference) o;
        return Objects.equals(sourceCode, that.sourceCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sourceCode);
    }
}
