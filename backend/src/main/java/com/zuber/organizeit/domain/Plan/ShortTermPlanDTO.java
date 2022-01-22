package com.zuber.organizeit.domain.Plan;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zuber.organizeit.domain.Tag;
import lombok.*;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter // todo remove it later
public class ShortTermPlanDTO {

    @JsonAlias({"planId", "plan_id", "id" })
    private Long id;
    private String name;
    @JsonAlias({"rootTasks", "rootTaskIds", "root_task_ids" })
    private List<Long> rootTaskIds;
    private String description;
    @JsonAlias({"isDone", "is_done", "done" })
    private boolean isDone;
    @JsonAlias({"isArchived", "is_archived", "archived" })
    private boolean isArchived;
    private List<Tag> tags;

}
