package com.zuber.organizeit.services.exporter;

import com.zuber.organizeit.Model.Repository.EntityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class Exporter {

    EntityDAO entityDAO;
    FSTreeParser fsTreeParser;

    @Autowired
    public Exporter(EntityDAO entityDAO, FSTreeParser fsTreeParser) {
        this.entityDAO = entityDAO;
        this.fsTreeParser = fsTreeParser;
    }

    public void initDb(Collection<Path> files) {

        Predicate<Path> isDirectory = p -> p.toFile().isDirectory();
        files.stream()
                .filter(isDirectory)
                .map(fsTreeParser::parse)
                .forEach();


//        files.forEach(file -> {
//            String fileName = file.getFileName().toString();
//            if (
//                    fileName.endsWith(".java") ||
//                            fileName.endsWith(".js") ||
//                            fileName.endsWith(".sh")
//            ) {
//                CodeFileParser.parse(file)
//                        .ifPresent(em::persist);
//            } else {
//                PseudoYamlParser.parseDir(file)
//                        .ifPresent(em::persist);
//            }
//        });


//        files.forEach();
        // Parser resolveParser(String name)
        // Parser<String, Optional<ParseResult>>.apply(name)
        //         
        
    }

}
