package com.zuber.organizeit.domain.Plan;


import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class TotalScore {

    private Long obtainedPoints;
    private Long totalPoints;

    public TotalScore(Long obtainedPoints, Long totalPoints) {
        this.obtainedPoints = obtainedPoints;
        this.totalPoints = totalPoints;
    }


}
