package com.zuber.organizeit.Model.Repository;

import com.zuber.organizeit.Model.Task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // todo czy nie trzeba hibernate listy uzyc tutaj?
    List<Task> findTaskByNameAndIsDoneIsFalse(String taskName);

}