package com.zuber.organizeit.services.exporters;

import com.zuber.organizeit.Model.Repository.EntityDAO;
import com.zuber.organizeit.services.exporters.old.SnippetParser;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.Collection;

@Service
public class SnippetExporterS {

    EntityDAO entityDAO;

    public SnippetExporterS(EntityDAO entityDAO) {
        this.entityDAO = entityDAO;
    }

    public void initDb(Collection<Path> dirs) {
        dirs.stream().map(SnippetParser::parse).flatMap(Collection::stream)
                .forEach(next -> entityDAO.save(next));
    }

}
