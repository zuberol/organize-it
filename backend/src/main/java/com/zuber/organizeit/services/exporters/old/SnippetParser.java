package com.zuber.organizeit.services.exporters.old;

import com.zuber.organizeit.Model.Snippet;
import com.zuber.organizeit.Model.Tag;
import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SnippetParser {

    private static final String SNIPPETS = "snippets";

    public static List<Snippet> parse(Path snippetsDir) {
        class CustomFileVisitor<T> extends SimpleFileVisitor<T> {
            public final List<Snippet> parsedSnippets = new ArrayList<>();
        }

        CustomFileVisitor<Path> customFileVisitor = new CustomFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path fileInDeckDir, BasicFileAttributes attrs) throws IOException {
                if(isSnippetFile(fileInDeckDir)) parsedSnippets.addAll(parseSnippetFile(fileInDeckDir));
                return super.visitFile(fileInDeckDir, attrs);
            }
        };

        try {
            Files.walkFileTree(snippetsDir, customFileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
            customFileVisitor.parsedSnippets.clear();
        }

        return customFileVisitor.parsedSnippets;
    }

    private static boolean isSnippetFile(Path file) {
        return file.getFileName().toString().endsWith(SNIPPETS);
    }

    private static List<Snippet> parseSnippetFile(Path snippetFile) throws IOException {
        List<Snippet> snippets = new ArrayList<>();
        Predicate<String> isSnippetTitle = line -> Pattern.compile("^[^\\s].*").matcher(line).matches();
        Predicate<String> isSnippetEnd = Strings::isBlank;

        Iterator<String> iterator = Files.readAllLines(snippetFile).iterator();
        Snippet curr = null;
        while (iterator.hasNext()) {
            String line = iterator.next();

            if (isSnippetEnd.test(line)) {
                if(curr != null) {
                    snippets.add(curr);
                    curr = null;
                }
            }
            else if(isSnippetTitle.test(line)) {
                if(curr == null) curr = new Snippet();
                curr.setTitle(line);

                if(curr.getTags() == null) curr.setTags(new ArrayList<>());
                Tag snippetDirName = new Tag();
//                snippetDirName.setAliases(List.of(snippetFile.getParent().getFileName().toString())); //todo doesnt search for available tags
                snippetDirName.setMainName(snippetFile.getParent().getFileName().toString() + "/" + snippetFile.getFileName().toString());
                curr.getTags().add(snippetDirName);

            }
            else {
                if(curr != null) {
                    if(curr.getContent() == null){
                        curr.setContent("");
                    }
                    curr.setContent(curr.getContent() + line);
                }
            }

            if(!iterator.hasNext() && curr != null) {
                snippets.add(curr);
                curr = null;
            }
        }


        return snippets;
    }

}