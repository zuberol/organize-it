package com.zuber.organizeit.services.exporter;


import com.zuber.organizeit.Model.Task.Task;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.zuber.organizeit.Model.Task.Task.ParsableProperty.*;
import static java.util.Optional.of;
import static java.util.stream.Collectors.*;

@Component
public class PseudoYAMLParser {

    // file parse
    private static final Function<String, Long> toLeadingTabs = line -> line.chars().takeWhile(ch -> ch == '\t').count();
    private static final String DESCRIPTION_FILE = "description";


    public static Optional<Task> parse(String file)  {
        Optional<Task> parsedTask = Optional.empty();
        final Task rootTask = Task.builder().name(file.strip()).build();
        final List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(file)).stream().filter(s -> !s.isBlank()).toList();
            Queue<Task> queue = lines.stream().map(Task::new)
                    .collect(toCollection(LinkedList::new));
            Task prev = rootTask;
            while (!queue.isEmpty()) {
                prev = findAncestor(queue.poll(), prev);
            }
            parsedTask = of(rootTask);
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found", e.getCause());
        }

        return parsedTask;
    }

    private static Task findAncestor(Task one, Task two) {
        Long onesTabs = toLeadingTabs.apply(one.getName());
        Long twosTabs = toLeadingTabs.apply(two.getName());

        Task child;
        Task oldestAncestor;
        if(onesTabs < twosTabs) {
            oldestAncestor = one; child = two;
        }
        else {
            oldestAncestor = two; child = one;
        }

        //append to youngestAncestor
        Task youngestAncestor = findYoungestAncestor(oldestAncestor, child);
        youngestAncestor.getSubTasks().add(child);

        return oldestAncestor;
    }

    private static Task findYoungestAncestor(Task ancestor, Task forChild) {
        List<Task> tasks = ancestor.getSubTasks().stream().takeWhile(directChild ->
                toLeadingTabs.apply(directChild.getName()) < toLeadingTabs.apply(forChild.getName())
        ).toList();
        return tasks.isEmpty() ? ancestor :  findYoungestAncestor(tasks.get(tasks.size()-1), forChild);

    }

    // dir parse
    public static Set<Linkage> parseDir(Path dirPath) {
        Set<Linkage> taskSubtaskLinkages = new HashSet<>();
        try {
            taskSubtaskLinkages = parseDirThrowable(dirPath);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error when parsing dir.", e.getCause());
        }
        return taskSubtaskLinkages;
    }

    public record Linkage(Task task, Task subtask){}
    public record LinkagePath(Path task, Path subtask){}

    private static Set<Linkage> parseDirThrowable(Path dirPath) throws Exception {
        if(!Files.isDirectory(dirPath)) throw new IllegalArgumentException("Not a directory.");
        HashSet<LinkagePath> linkages = new HashSet<>();
        HashMap<Path, Task> spottedTasks = new HashMap<>();
        SimpleFileVisitor<Path> fileVisitor = new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Function<Path, Stream<? extends String>> sneakyFileToLines = f -> {
                    Stream<String> lines = Stream.empty();
                    try {
                        lines = Files.readAllLines(f).stream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return lines;
                };

                Map<String, String> parsedTasksProperties = of(file).filter(f -> f.getFileName().toString().equals(DESCRIPTION_FILE))
                        .stream()
                        .flatMap(sneakyFileToLines)
                        .map(line -> line.split("=", 2))
                        .filter(props -> props.length == 2)
                        .collect(toMap(arr -> arr[0], arr -> arr[1]));


                Task task = spottedTasks.getOrDefault(file.getParent(), Task.builder()
                        .name(file.getParent().getFileName().toString())
                        .locallySavedURI(file.getParent().toAbsolutePath().toString())
                        .build());
                task.setName(parsedTasksProperties.getOrDefault(NAME.name(), file.getParent().getFileName().toString()));
                task.setDescription(parsedTasksProperties.getOrDefault(DESCRIPTION.name(), "..."));
                task.setProject(Boolean.parseBoolean(parsedTasksProperties.getOrDefault(IS_PROJECT.name(), "false")));
                spottedTasks.put(file.getParent(), task);

                Task subTask = spottedTasks.getOrDefault(file, Task.builder()
                        .name(file.getFileName().toString())
                        .locallySavedURI(file.toAbsolutePath().toString())
                        .build());
                spottedTasks.put(file, subTask);

                linkages.add(new LinkagePath(file.getParent(), file));
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Task task = spottedTasks.getOrDefault(dir.getParent(), Task.builder()
                        .name(dir.getParent().getFileName().toString())
                        .locallySavedURI(dir.getParent().toAbsolutePath().toString())
                        .build());
                spottedTasks.put(dir.getParent(), task);
                Task subTask = spottedTasks.getOrDefault(dir, Task.builder()
                        .name(dir.getFileName().toString())
                        .locallySavedURI(dir.toAbsolutePath().toString())
                        .build());

                spottedTasks.put(dir, subTask);

                linkages.add(new LinkagePath(dir.getParent(), dir));
                return super.preVisitDirectory(dir, attrs);
            }

        };

        Files.walkFileTree(dirPath, fileVisitor);

        return linkages.stream().map(linkagePath -> new Linkage(
                spottedTasks.get(linkagePath.task),
                spottedTasks.get(linkagePath.subtask)
        )).collect(toSet());
    }

}

