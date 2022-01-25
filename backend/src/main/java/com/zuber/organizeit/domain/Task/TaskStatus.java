package com.zuber.organizeit.domain.Task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Builder
@Getter
@AllArgsConstructor
@Setter
public class TaskStatus {

    protected TaskStatus() {}

    @Column(name = "priority")
    @Builder.Default
    private Long priority = 1000L;

    @Column(unique = true)
    private String locallySavedURI;



    @Builder.Default
    private Done done = Done.builder().build();


    @Builder.Default
    private Archived archived = Archived.builder().build();




    @Embedded
    @Builder.Default
    private TimeEstimates timeEstimates = TimeEstimates.builder().build();



}

