package com.zuber.organizeit.services.planner;


import com.zuber.organizeit.domain.Plan.ShortTermPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputeStatusService {

    private final ShortTermPlanRepository shortTermPlanRepository;

    @Autowired
    public ComputeStatusService(ShortTermPlanRepository shortTermPlanRepository) {
        this.shortTermPlanRepository = shortTermPlanRepository;
    }



}
