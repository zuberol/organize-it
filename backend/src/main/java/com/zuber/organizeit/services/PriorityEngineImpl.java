package com.zuber.organizeit.services;

import com.zuber.organizeit.Model.PriorityPoint;
import com.zuber.organizeit.Model.StandardPriorityPoint;

public class PriorityEngineImpl implements PriorityEngine {
    @Override
    public PriorityPoint computePriority() {
        StandardPriorityPoint standardPriorityPoint = new StandardPriorityPoint();
        standardPriorityPoint.setPoint(5L);
        return standardPriorityPoint;
    }
}
