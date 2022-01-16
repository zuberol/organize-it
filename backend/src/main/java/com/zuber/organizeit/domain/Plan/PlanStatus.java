package com.zuber.organizeit.domain.Plan;

import com.zuber.organizeit.domain.Status;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.zuber.organizeit.utils.Utils.not;

@Embeddable
public
class PlanStatus implements Status {

    @Column(name = "whenDone")
    LocalDateTime when;

    public boolean isDone() {
        return not(Objects.isNull(when));
    }


}
