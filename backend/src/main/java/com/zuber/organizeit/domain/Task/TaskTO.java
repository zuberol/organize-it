package com.zuber.organizeit.domain.Task;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@AllArgsConstructor
@NoArgsConstructor @Setter
public class TaskTO {
    @JsonAlias({"id", "taskId", "task_id" })
    @Nullable Long id;
    @Nullable String name;
    @Nullable String description;
    @JsonAlias({"subtaskIds", "subtask_ids", "subTasks" })
    @Nullable List<Long> subtaskIds;
    @JsonAlias({"isDone", "done", "is_done" })
    @Nullable Boolean isDone;
    @JsonAlias({"isArchived", "archived", "is_archived" })
    @Nullable Boolean isArchived;
    @Nullable Long priority;
    private List<String> tags;
    @JsonAlias({"plan", "plan_id"})
    private Long planId;
    @JsonAlias({"parent_task", "parentTask"})
    private Long parentTaskId;

}
