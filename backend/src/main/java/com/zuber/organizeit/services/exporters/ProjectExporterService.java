package com.zuber.organizeit.services.exporters;

import com.zuber.organizeit.Model.Repository.EntityDAO;
import com.zuber.organizeit.Model.Task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

@Service
public class ProjectExporterService {

    private final EntityDAO entityDAO;

    @Autowired
    public ProjectExporterService(EntityDAO entityDAO) {
        this.entityDAO = entityDAO;
    }

    public void initDb(Collection<Path> dirs) {
        dirs.stream()
                .map(PseudoYAMLParser::parseDir)
                .flatMap(Collection::stream).toList()
                .stream().filter(linkage -> !linkage.subtask().getName().equals("description"))
                .forEach(linkage -> {
                    Task task = mergeWithPersistedTask(linkage.task());
                    Task subtask = mergeWithPersistedTask(linkage.subtask());
                    task.getSubTasks().add(subtask);
                    entityDAO.save(task);
                });
    }

    private Task mergeWithPersistedTask(Task transientTask) {
        Task persistedTask;
        Optional<Task> persistedTaskOpt = entityDAO.findByLocallySavedURI(transientTask.getLocallySavedURI());
        if (persistedTaskOpt.isPresent()) {
            Task taskFromDb = persistedTaskOpt.get();
            ofNullable(transientTask.getTaskId()).ifPresent(taskFromDb::setTaskId);
            ofNullable(transientTask.getName()).ifPresent(taskFromDb::setName);
            ofNullable(transientTask.getDescription()).ifPresent(taskFromDb::setDescription);
            ofNullable(transientTask.getLocallySavedURI()).ifPresent(taskFromDb::setLocallySavedURI);
            of(transientTask.isProject()).ifPresent(taskFromDb::setProject);
            persistedTask = entityDAO.save(taskFromDb);
        }
        else {
            persistedTask = entityDAO.save(transientTask);
        }
        return persistedTask;
    }


}
