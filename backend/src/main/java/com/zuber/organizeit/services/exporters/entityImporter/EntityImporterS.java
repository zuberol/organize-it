package com.zuber.organizeit.services.exporters.entityImporter;

import com.zuber.organizeit.domain.BaseEntity;
import com.zuber.organizeit.services.exporters.entityImporter.exceptions.LoadingFileFailed;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.readAllLines;
import static java.util.Collections.unmodifiableList;

@Service
public class EntityImporterS {

    private final ParseCtxFactory ctxFactory;

    public EntityImporterS(ParseCtxFactory ctxFactory) {
        this.ctxFactory = ctxFactory;
    }


    public <E extends BaseEntity<Long>> E importEntity(Class<E> entity, Path filePath) {
        E importedEntity;
        try {
            YmlTreeParser<E> parser = new YmlTreeParser<>(ctxFactory, entity);
            importedEntity = parser.run(unmodifiableList(readAllLines(filePath))).orElse(null); // todo
        } catch (IOException e) {
            throw new LoadingFileFailed(e);
        }
        return importedEntity;
    }



}
