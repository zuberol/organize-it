package com.zuber.organizeit.domain.Plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortTermPlanRepository extends JpaRepository<ShortTermPlan, Long> {

    Optional<ShortTermPlan> findPlanByName(String name);

}
