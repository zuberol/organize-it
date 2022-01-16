package com.zuber.organizeit.domain.Plan;

import com.zuber.organizeit.domain.BaseAggregateRoot;

import java.util.List;

public class IdeaLongTermPlan extends BaseAggregateRoot<Long> {



    List<ShortTermPlan> shortTermPlans;




}
