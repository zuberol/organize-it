package com.zuber.organizeit.domain.Plan;

import com.zuber.organizeit.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.zuber.organizeit.utils.Utils.not;

@Embeddable
@Builder @AllArgsConstructor
@Setter
public class PlanStatus implements Status {

    protected PlanStatus (){}

    @Column(name = "whenDone")
    LocalDateTime whenDone;
    LocalDateTime whenArchived;

    public boolean isDone() {
        return not(Objects.isNull(whenDone));
    }

    public boolean isArchived() {
        return not(Objects.isNull(whenArchived));
    }

}
