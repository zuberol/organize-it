package com.zuber.organizeit.services.exporters.parser.rm;

import com.zuber.organizeit.Model.Snippet;
import com.zuber.organizeit.services.exporters.parser.ParseContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


// flashcard, notes, snippets, refs
@Component
public class NoteDirStructureParser {


//    private final ApplicationContext appContext;
//
//    public NoteDirStructureParser(ApplicationContext appContext) {
//        this.appContext = appContext;
//    }
//
//

    <E> ParseContext<E> merge(ParseContext<E> parseCtx) {


        return parseCtx;
    }




//        private class ParsableCtx<C extends Parsable> {
//
//
//
//    }

//
//    interface ParsableDef {
//
//    }
//
//    <T> hehe(Class<T> entity) {
//
//
//
//        return null;
//    }


    public static void parseDirStructure(Path rootDir) {
        try {
            checkDirsUniqueness(rootDir);
            parseDirStructureThrows(rootDir);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    private static void parseDirStructureThrows(Path rootDir) {
        final SimpleFileVisitor<Path> fileVisitor = new SimpleFileVisitor<>(){
            private final List<Snippet> parsedSnippets = new ArrayList<>(); // todo jaki typ?
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String s = file.getFileName().toString();
                System.out.println(s);

                return super.visitFile(file, attrs);
            }
        };


        try {
            Files.walkFileTree(rootDir, fileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("hehe");


    }

    private static void checkDirsUniqueness(Path rootDir) throws IllegalStateException {
        try (Stream<Path> walk = Files.walk(rootDir)) {
            List<String> files = walk
                    .map(p -> p.getFileName().toString()).toList();
            boolean duplicateFound = files.stream().distinct().toList().size() != files.size();
            if(duplicateFound) throw new IllegalStateException("Some directory appeared twice. Skipping...");
        } catch (IOException e) {
           throw new IllegalStateException("Dir not found.");
        }
    }





    // todo
//    @Override
//    List<ParsableDef<?>> resolveParsableDefs(){
//        return List.of();
//    }
//
//    void parse(Path file) throws IOException {
//        List<String> strings = Files.readAllLines(file);
//
//        // for each line
//        String line = "";
////        parseLine()
//        ParsableCtx<Note> context;
//
//        Function<Supplier<List<String>>, Parsable> parserBuilder = linesSupplier -> {
//            final List<String> lines = linesSupplier.get();
//
//
//
//
//        }
//
//    }
//
//    String [] split(String line) {
////        Pattern.compile("") // todo
//    }
//
//


//    public static void main(String[] args) {
//        Path rootPathTest = Path.of("/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/exporters/testRootNote");
//        NoteDirStructureParser.parseDirStructure(rootPathTest);
//    }



}


