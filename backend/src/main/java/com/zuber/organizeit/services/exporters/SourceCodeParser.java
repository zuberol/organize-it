package com.zuber.organizeit.services.exporters;

import com.zuber.organizeit.Model.Flashcard.CodeReference;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;


//@Component
public class SourceCodeParser implements FileParser<CodeReference> {

    public static Optional<CodeReference> parse(Path sourceCode) {
        return parseThrows(sourceCode);
    }

    private static Optional<CodeReference> parseThrows(Path sourceCodePath) {
        Optional<CodeReference> sourceCodeRef = Optional.empty();
        try {
            sourceCodeRef = Files.readAllLines(sourceCodePath)
                    .stream()
                    .reduce((one, two) -> one + "\\n" + two)
                    .map(code -> {
                        CodeReference codeReference = new CodeReference(); //todo Superbuilder
                        codeReference.setCaption(sourceCodePath.getFileName().toString());
                        codeReference.setSourceCode(code);
                        codeReference.setLocallySavedURI(sourceCodePath.toString());
                        return codeReference;
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sourceCodeRef;
    }


}
