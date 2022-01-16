package com.zuber.organizeit.domain.Plan;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter // todo remove it later
public class PlanDTO {

    @JsonAlias({"planId", "plan_id", "id" })
    private Long id;
    private String name;
    @JsonAlias({"rootTaskIds", "root_task_ids" })
    private List<Long> rootTaskIds;
    private String description;

}
