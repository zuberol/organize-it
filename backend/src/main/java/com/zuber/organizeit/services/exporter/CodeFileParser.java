package com.zuber.organizeit.services.exporter;

import com.zuber.organizeit.Model.Flashcard.CodeReference;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;


@Component
public class CodeFileParser implements FileParser<CodeReference> {

    public  Optional<CodeReference> parse(Path codeFilePath) {
        return parseThrows(codeFilePath);
    }

    private  Optional<CodeReference> parseThrows(Path codeFilePath) {
        Optional<CodeReference> sourceCodeRef = Optional.empty();
        try {
            sourceCodeRef = Files.readAllLines(codeFilePath)
                    .stream()
                    .reduce((one, two) -> one + "\\n" + two)
                    .map(CodeReference::new);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sourceCodeRef;
    }


}
