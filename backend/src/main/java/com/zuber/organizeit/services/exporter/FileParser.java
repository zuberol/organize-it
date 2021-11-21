package com.zuber.organizeit.services.exporter;


import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Function;

public interface FileParser <R> {

    Optional<R> parse(Path file);
//
//    public static Task parse(Path ymlPath) {
//        try {
//            Files.
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

//    default boolean isSupportedFileType() { //todo nie mozna defaultow w abstract klasie
//        return true;
//    }


}
