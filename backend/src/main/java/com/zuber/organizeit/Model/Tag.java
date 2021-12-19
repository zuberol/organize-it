package com.zuber.organizeit.Model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name = "tags")
public class Tag {

    public Tag(String mainName) {
        this.mainName = mainName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id")
    private Long id;

    private String mainName; //todo should be one of aliases

    @ElementCollection
    List<String> aliases;


}
