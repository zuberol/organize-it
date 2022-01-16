package com.zuber.organizeit.services.exporters.entityImporter;

import com.zuber.organizeit.domain.BaseEntity;

import java.util.Iterator;
import java.util.function.BiConsumer;

@FunctionalInterface
public interface MergeActionDef<E extends BaseEntity<Long>, C extends ParseCtx<E>> extends BiConsumer<C, Iterator<String>> {

}
