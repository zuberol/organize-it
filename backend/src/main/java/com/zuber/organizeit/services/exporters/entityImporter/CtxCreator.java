package com.zuber.organizeit.services.exporters.entityImporter;

import com.zuber.organizeit.domain.BaseEntity;

public interface CtxCreator {


    <E extends BaseEntity<Long>> ParseCtx<E> getLevelAwareNewCtx(Class<E> initCtx);
    <E extends BaseEntity<Long>> ParseCtx<E> getLevelAwareNewCtx(Class<E> initCtx, int level);
    ParseCtx<?> getLevelAwareNewCtx(String ctxName, int level);
}
