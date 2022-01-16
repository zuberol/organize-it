package com.zuber.organizeit.domain.Task;


import lombok.*;

import javax.persistence.*;

@Embeddable
@Table(name = "priority_points")
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED) @Getter @Setter // todo remove setters, go immutable
public class PriorityPoint {
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "priority")
    private Long point = 1000L;

}


// klikniecie - powinno wrzucic o jedna pozycje w gore/dol
// priority powinno byc zalezne od deadlineu
    // dodac deadline do taska
    // sortowanie taskow po deadline
    // rootEntity changed
    // co sie stanie jak zmienie tasks ktory jest subtaskiem innego taska


//entity Project
    // Goal
//      deadline
