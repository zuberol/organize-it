package com.zuber.organizeit.services.parser;

import com.zuber.organizeit.Model.DevDTO;
import com.zuber.organizeit.Model.EntityIdentity;
import com.zuber.organizeit.Model.Task;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PseudoYamlParser {

    // file parse
    private static final Function<String, Long> toLeadingTabs = line -> line.chars().takeWhile(ch -> ch == '\t').count();

    public static Optional<Task> parse(String file)  {
        Optional<Task> parsedTask = Optional.empty();
        final Task rootTask = Task.builder().name(file.strip()).build();
        final List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(file)).stream().filter(s -> !s.isBlank()).toList();
            Queue<Task> queue = lines.stream().map(Task::new)
                    .collect(Collectors.toCollection(LinkedList::new));
            Task prev = rootTask;
            while (!queue.isEmpty()) {
                prev = findAncestor(queue.poll(), prev);
            }
            parsedTask = Optional.of(rootTask);
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
    public static Optional<Task> parseDir(String dirPath) {
        Optional<Task> parsedTask = Optional.empty();
        try {
            parsedTask = parseDirThrowable(dirPath);
        } catch (IOException e) {
            throw new IllegalArgumentException("Dir not found", e.getCause());
        }
        return parsedTask;
    }

    private static Optional<Task> parseDirThrowable(String dirPath) throws IOException {
        Optional<Task> parsedTask = Optional.empty();

        final Task rootTask = Task.builder().name(dirPath).build();

        populateDTOWithTasks(dirPath);

        return parsedTask = Optional.of(rootTask);
    }

    public static void populateDTOWithTasks(String dirPath) throws IOException {

        Files.walkFileTree(Path.of(dirPath), new SimpleFileVisitor<>(){

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                saveNameToMap(file, file.getParent());
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                saveNameToMap(dir, dir.getParent());
                return super.preVisitDirectory(dir, attrs);
            }

            private void saveNameToMap(Path tosave, Path parent) {
                Task child = DevDTO.save(
                        new EntityIdentity(null, tosave.toAbsolutePath().toString()),
                        Task.builder().name(tosave.getFileName().toString()).description(tosave.getFileName().toString()).build());
                EntityIdentity parentIdentity = new EntityIdentity(null, parent.toAbsolutePath().toString());
                DevDTO.get(parentIdentity)
                        .ifPresent(parentTask -> parentTask.getSubTasks().add(child));
            }
        });
    }


}
