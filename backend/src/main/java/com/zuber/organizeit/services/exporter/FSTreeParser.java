package com.zuber.organizeit.services.exporter;

import com.zuber.organizeit.Model.Task;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Function;



@Component
public class FSTreeParser implements FileParser<Task> {


    @Override
    public Optional<Task> parse(Path path) {
        return Optional.empty();
    }

}
