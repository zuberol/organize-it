package com.zuber.organizeit.domain.Task;

import lombok.*;

import javax.persistence.Embeddable;
import java.sql.Timestamp;
import java.time.Instant;

@Embeddable
@Builder // @AllArgsConstructor @Getter
//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Getter
@AllArgsConstructor
public class TimeEstimates {
    @Builder.Default Timestamp created = new Timestamp(Instant.now().toEpochMilli());
    @Builder.Default Timestamp estimatedEnd = new Timestamp(0);
    @Builder.Default Timestamp spent = new Timestamp(0);

    protected TimeEstimates() {}
}
