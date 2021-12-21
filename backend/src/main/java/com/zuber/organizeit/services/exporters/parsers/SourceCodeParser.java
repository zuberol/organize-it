package com.zuber.organizeit.services.exporters.parsers;

import com.zuber.organizeit.Model.Note.ReferenceResource.CodeReference;
import com.zuber.organizeit.services.exporters.parsers.scratches.FileParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;


//@Component
public class SourceCodeParser implements FileParser<CodeReference> {

    private static final String JAVA_SUFFIX = ".java";

    public static Optional<CodeReference> parse(Path sourceCode) {
        return parseThrows(sourceCode);
    }

    private static Optional<CodeReference> parseThrows(Path sourceCodePath) {
        Optional<CodeReference> sourceCodeRef = Optional.empty();
        try {
            if(!sourceCodePath.toString().endsWith(JAVA_SUFFIX)) throw new ParseException(sourceCodePath+" "+"must have a \".java\" suffix");
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

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        return sourceCodeRef;
    }


}
