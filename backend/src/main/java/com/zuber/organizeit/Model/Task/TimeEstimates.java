package com.zuber.organizeit.Model.Task;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.persistence.Embeddable;
import java.sql.Timestamp;
import java.time.Instant;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class TimeEstimates {
    @Builder.Default Timestamp whenCreated = new Timestamp(Instant.now().toEpochMilli());
    @Builder.Default Timestamp timeEstimated = new Timestamp(0);
    @Builder.Default Timestamp timeSpent = new Timestamp(0);
    @Builder.Default int accFactor = 1;
}
