package com.zuber.organizeit.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VideoReference extends ReferenceResource {
    private String referenceUrl; //todo use URL ?
}
