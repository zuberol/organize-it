package com.zuber.organizeit.domain.Plan;

import com.zuber.organizeit.domain.BaseAggregateRoot;
import com.zuber.organizeit.domain.Task.Task;
import lombok.*;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.*;

import static java.util.Optional.ofNullable;

@Entity @Builder
@AllArgsConstructor @NoArgsConstructor @Getter @Setter // setter nie potrzebny raczej
public class ShortTermPlan extends BaseAggregateRoot<Long> {

    private String name;

    private String description;

    @ManyToMany//(cascade = {DETACH, MERGE, PERSIST, REFRESH})
    @Builder.Default
    private List<Task> rootTasks = new LinkedList<>();

    @Nullable @Transient
    private transient List<Task> allPlanTasks;

//    @Embedded
//    Status<Plan> statusType;

    public List<Task> getAllPlanTasks() {
        if(allPlanTasks == null) setAllPlanTasks(flattenRootTasks(rootTasks));
        return allPlanTasks;
    }

    private static List<Task> flattenRootTasks(Collection<Task> rootTasks) {
        return rootTasks.stream()
                .map(rootTask -> flattenRootTasks(rootTask, new LinkedList<>()))
                .flatMap(List::stream)
                .toList();
    }

    private static List<Task> flattenRootTasks(Task toUnpack, List<Task> memo) {
        memo.addAll(toUnpack.getSubTasks());
        toUnpack.getSubTasks().forEach(task -> flattenRootTasks(task, memo));
        return Collections.unmodifiableList(memo);
    }

    public ShortTermPlan modifyBasicData(PlanDTO dto) {
        ofNullable(dto.getName()).ifPresent(this::setName);
        ofNullable(dto.getDescription()).ifPresent(this::setDescription);
        return this;
    }

    @Embedded
    private PlanStatus status;

    static PlanStatus resolveStatus() {
        return null;
    }

}
