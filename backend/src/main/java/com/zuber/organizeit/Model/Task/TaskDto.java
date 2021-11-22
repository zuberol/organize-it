package com.zuber.organizeit.Model.Task;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@AllArgsConstructor
@NoArgsConstructor @Setter // todo remove it later
public class TaskDto {
    @Nullable Long taskId;
    @Nullable String name;
    @Nullable String description;
    @Nullable List<Long> subtaskIds;
    @Nullable Boolean isDone;
    @Nullable Boolean isArchived;
}
