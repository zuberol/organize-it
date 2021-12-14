package com.zuber.organizeit.services.exporters;

import com.zuber.organizeit.Model.Note.ReferenceResource.CodeReference;
import com.zuber.organizeit.Model.Note.Flashcard.Flashcard;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class FlashcardParser {

    public static List<Flashcard> parse(Path decksDir) {
        class CustomFileVisitor<T> extends SimpleFileVisitor<T> {
            public final List<Flashcard> parsedFlashcards = new ArrayList<>();
        }

        CustomFileVisitor<Path> customFileVisitor = new CustomFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path fileInDeckDir, BasicFileAttributes attrs) throws IOException {
                if(fileInDeckDir.getFileName().toString().endsWith("flashcards")) {
                    parsedFlashcards.addAll(parseFlashcardFile(fileInDeckDir));
                }
                return super.visitFile(fileInDeckDir, attrs);
            }
            // todo take care of flashcards in nested directories
        };

        try {
            Files.walkFileTree(decksDir, customFileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
            customFileVisitor.parsedFlashcards.clear();
        }

        return customFileVisitor.parsedFlashcards;
    }


    private static List<Flashcard> parseFlashcardFile(Path flashcardPath) throws IOException {
        Iterator<String> iterator = Files.readAllLines(flashcardPath).iterator(); //todo throws
        Predicate<String> isQuestion = Pattern.compile("^[^\\s].*$").asMatchPredicate();
        Predicate<String> isCodeRef = Pattern.compile("^\\s+CodeRef: .*\\..*").asPredicate();

        ArrayList<Flashcard> flashcards = new ArrayList<>();

        Flashcard mutableCurr = null;
        while (iterator.hasNext()) {
            String line = iterator.next();
            if(mutableCurr == null) mutableCurr = new Flashcard();
            if(isQuestion.test(line)) mutableCurr.setQuestion(line);
            else if(Strings.isBlank(line)) flashcards.add(mutableCurr);
            else if(isCodeRef.test(line)) {
                Optional<CodeReference> codeReference = SourceCodeParser
                        .parse(resolveCodePath(flashcardPath.getParent(), line));

                if (mutableCurr.getReferenceResources() == null) {
                    mutableCurr.setReferenceResources(new LinkedList<>());
                }
                if(codeReference.isPresent()) mutableCurr.getReferenceResources().add(codeReference.get());

            }
            else {
                if(mutableCurr.getLongAnswer() != null) mutableCurr.setLongAnswer(mutableCurr.getLongAnswer().concat(line));
                else mutableCurr.setLongAnswer(line);

            }


            if(!iterator.hasNext()) flashcards.add(mutableCurr);
        }

        //todo if empty fc -> flashcards.remove(fc)

        return flashcards;
    }

    private static Path resolveCodePath(Path flashcardPath, String parsedLine) {
        return Paths.get(flashcardPath.toString(), parsedLine.replace("CodeRef: ", "").strip());
    }

}
