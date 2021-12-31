package com.zuber.organizeit.Model.Task;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@AllArgsConstructor
@NoArgsConstructor @Setter // todo remove it later
public class TaskDTO {
    @JsonAlias({"taskId", "task_id" })
    @Nullable Long taskId;
    @Nullable String name;
    @Nullable String description;
    @JsonAlias({"subtaskIds", "subtask_ids", "subtasks" })
    @Nullable List<Long> subtaskIds;
    @JsonAlias({"isDone", "done", "is_done" })
    @Nullable Boolean isDone;
    @JsonAlias({"isArchived", "archived", "is_archived" })
    @Nullable Boolean isArchived;
    @JsonAlias({"isProject", "project", "is_project" })
    @Nullable Boolean isProject;
    @Nullable Long priority;
}
