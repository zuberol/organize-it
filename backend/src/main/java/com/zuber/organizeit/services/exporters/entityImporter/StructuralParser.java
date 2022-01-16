package com.zuber.organizeit.services.exporters.entityImporter;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public interface StructuralParser<E> {

    Optional<E> run(List<String> lines);

}
