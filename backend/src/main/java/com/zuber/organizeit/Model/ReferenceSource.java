package com.zuber.organizeit.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "referenceSources")
public class ReferenceSource implements Serializable {

    @Id
    private Long id;
    private String caption;
    private String comment;
    private String referenceUrl;
    private Boolean isUrlTypeRef;
    private Boolean isBookTypeRef;

}
