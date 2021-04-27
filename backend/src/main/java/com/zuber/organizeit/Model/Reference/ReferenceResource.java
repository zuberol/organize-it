package com.zuber.organizeit.Model.Reference;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.net.URI;

@Entity
@Getter @Setter
@Table(name = "referenceResources")
@Inheritance
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public abstract class ReferenceResource implements Serializable {

    public final static String ID_SEQ_NAME = "reference_resource_seq";

    @Id
    private Long id;
    private String caption;
    private String comment;
//    private String referenceUrl;
//    private Boolean isUrlTypeRef;
//    private Boolean isBookTypeRef;
//    private URI uri;

}
