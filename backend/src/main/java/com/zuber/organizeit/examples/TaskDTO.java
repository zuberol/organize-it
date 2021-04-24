package com.zuber.organizeit.examples;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
//@JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
@JsonIgnoreProperties(ignoreUnknown=true)
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    Long taskId;
    Long parentTaskId;
    List<Long> subTasksIds;
    String note;

}
