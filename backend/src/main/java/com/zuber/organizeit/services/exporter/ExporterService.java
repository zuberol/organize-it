package com.zuber.organizeit.services.exporter;

import com.zuber.organizeit.Model.Repository.EntityDAO;
import com.zuber.organizeit.Model.Task.Task;
import org.apache.commons.fileupload.util.Streams;
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
import java.util.stream.Stream;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

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
                .flatMap(Collection::stream)
//                .peek(System.out::println)
                .collect(Collectors.toList())
                .forEach(linkage -> {
                    Task task = mergeWithPersistedTask(linkage.task());
                    Task subtask = mergeWithPersistedTask(linkage.subtask());
                    task.getSubTasks().add(subtask);
                    entityDAO.save(task);
                });

        Integer integer = new Integer(1);
    }

    @NotNull
    private Function<Task, Task> replaceOneWith(Task savedSubtask) {
        return tsk ->
             !Objects.equals(tsk.getTaskId(), savedSubtask.getTaskId()) ?
                    tsk : savedSubtask;
    }

    // todo
    private Task mergeWithPersistedTask(Task taskTO) {
        Task mergedTask;
        Optional<Task> taskFromDbOpt = entityDAO.findByLocallySavedURI(taskTO.getLocallySavedURI());
        if (taskFromDbOpt.isPresent()) {
            Task taskFromDb = taskFromDbOpt.get();
            ofNullable(taskTO.getTaskId()).ifPresent(taskFromDb::setTaskId);
            ofNullable(taskTO.getName()).ifPresent(taskFromDb::setName);
            ofNullable(taskTO.getDescription()).ifPresent(taskFromDb::setDescription);
            ofNullable(taskTO.getLocallySavedURI()).ifPresent(taskFromDb::setLocallySavedURI);

//            Stream<Task> subtasksFromTo = taskTO.getSubTasks().stream().map(subtask -> entityDAO.findByLocallySavedURI(subtask.getLocallySavedURI()))
//                    .flatMap(Optional::stream);
//            Stream<Task> subtasksFromDb = taskFromDb.getSubTasks().stream();
//            List<Task> subtasks = Stream.concat(subtasksFromTo, subtasksFromDb).collect(Collectors.toList());
//            taskFromDb.setSubTasks(subtasks);

            mergedTask = entityDAO.save(taskFromDb);
        }
        else {
            mergedTask = entityDAO.save(taskTO);
        }
        return mergedTask;
    }


}
