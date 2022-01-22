package com.zuber.organizeit.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder @Getter @Setter @AllArgsConstructor
@Table(name = "tags")
public class Tag extends BaseEntity<Long>{

    protected Tag() {}

    public Tag(String mainName) {
        this.mainName = mainName;
    }

    @Column(unique = true)
    private String mainName; //todo should be one of aliases

    @ElementCollection
    List<String> aliases;

}
