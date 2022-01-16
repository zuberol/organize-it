package com.zuber.organizeit.domain;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Optional;

@MappedSuperclass
public abstract class BaseEntity<Id extends Serializable> extends AbstractPersistable<Id> {

    @Version
    private Long version;

    public @NotNull Optional<Long> getVersion() {
        return Optional.ofNullable(version);
    }

    protected void setVersion(@Nullable Long version) {
        this.version = version;
    }

}