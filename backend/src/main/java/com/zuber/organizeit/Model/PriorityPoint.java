package com.zuber.organizeit.Model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Embeddable
@Getter @Setter
@Table(name = "priority_points")
@Inheritance
public abstract class PriorityPoint {
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public Long point;



}
