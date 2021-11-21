package com.zuber.organizeit.Model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;

//@Embeddable
//@DiscriminatorValue("1")
public class StandardPriorityPoint extends PriorityPoint {

    private Long point;

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }
}
