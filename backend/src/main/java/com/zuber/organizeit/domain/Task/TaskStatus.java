package com.zuber.organizeit.domain.Task;

import com.zuber.organizeit.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embedded;


@Builder
@Getter
@AllArgsConstructor
public class TaskStatus {

    protected TaskStatus() {}

    @Column(name = "priority")
    @Builder.Default
    private Long priority = 1000L;

    @Column(unique = true)
    private String locallySavedURI;



    private Done done;

    private Archived archived;




    @Embedded
//    @Builder.Default
    private TimeEstimates timeEstimates = TimeEstimates.builder().build();



}
