package com.zuber.organizeit.domain.Repository;

import com.zuber.organizeit.domain.Task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // todo czy nie trzeba hibernate listy uzyc tutaj?
//    List<Task> findTaskByNameAndIsDoneIsFalse(String taskName);

//    Optional<Task> findTaskByLocallySavedURI(String uri);

    Optional<Task> findTaskByName(String name);

    @Query(nativeQuery = true, value =
            """
            select * from tasks t
             where t.id not in (select ts.sub_tasks_id from tasks_sub_tasks ts)
              and t.id not in (select s.root_tasks_id from short_term_plan_root_tasks s)
              and t.is_archived = false  and is_done = false
            """
            )
    List<Task> isNotSubtaskAndIsNotProject();

}