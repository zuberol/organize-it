package com.zuber.organizeit.services.exporter;

import com.zuber.organizeit.Model.Repository.EntityDAO;
import com.zuber.organizeit.Model.Task.Task;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ExporterService {

    private final EntityDAO entityDAO;

    @Autowired
    public ExporterService(EntityDAO entityDAO) {
        this.entityDAO = entityDAO;
    }

    public void initDb(Collection<Path> dirs) {
        dirs.stream()
                .map(PseudoYAMLParser::parseDir)
                .collect(Collectors.toList())
                .forEach(linkages -> linkages.forEach((task, subtask) -> {
                    task = saveMergedTask(task);
                    final Task savedSubtask = saveMergedTask(subtask);
                    List<Task> filteredSubtasks = task.getSubTasks()
                            .stream()
                            .map(replaceOneWith(savedSubtask))
                            .toList();
                    task.setSubTasks(filteredSubtasks);
                    entityDAO.save(task);
                }));
    }

    @NotNull
    private Function<Task, Task> replaceOneWith(Task savedSubtask) {
        return tsk ->
             !Objects.equals(tsk.getTaskId(), savedSubtask.getTaskId()) ?
                    tsk : savedSubtask;
    }

    // todo
    private Task saveMergedTask(Task task) {
        Optional<Task> savedTaskOpt = entityDAO.findByLocallySavedURI(task.getLocallySavedURI());
        if (savedTaskOpt.isPresent()) {
            Task toMerge = savedTaskOpt.get();
            Optional.ofNullable(toMerge.getTaskId()).ifPresent(task::setTaskId);
            Optional.ofNullable(toMerge.getName()).ifPresent(task::setName);
            Optional.ofNullable(toMerge.getDescription()).ifPresent(task::setDescription);
            Optional.ofNullable(toMerge.getLocallySavedURI()).ifPresent(task::setLocallySavedURI);
            Optional.ofNullable(toMerge.getSubTasks()).ifPresent(task::setSubTasks); //todo broken
        }
        return entityDAO.save(task);
    }

}
