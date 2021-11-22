package com.zuber.organizeit.Model.Repository;

import com.zuber.organizeit.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // todo czy nie trzeba hibernate listy uzyc tutaj?
    List<Task> findTaskByNameAndIsDoneIsFalse(String taskName);
//    List<Task> findTaskByNameAndDoneIsFalse(String taskName);

}