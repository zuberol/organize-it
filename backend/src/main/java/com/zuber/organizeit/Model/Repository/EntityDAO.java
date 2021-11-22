package com.zuber.organizeit.Model.Repository;

import com.zuber.organizeit.Model.Task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class EntityDAO {

    final TaskRepository taskRepository;
    final EntityManager em;

    @Autowired
    public EntityDAO(TaskRepository taskRepository, EntityManager em) {
        this.taskRepository = taskRepository;
        this.em = em;
    }

    public List<Task> findUndoneTasks(String taskName) {
        return taskRepository.findTaskByNameAndIsDoneIsFalse(taskName);
    }

    public void save(Object entity) {
        em.persist(entity);
    }
}
