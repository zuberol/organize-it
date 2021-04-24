package com.zuber.organizeit.Model.Repository;

import com.zuber.organizeit.Model.ReferenceResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class ImageRefResource extends ReferenceResource {
    private String refImage; //todo make uri
}
