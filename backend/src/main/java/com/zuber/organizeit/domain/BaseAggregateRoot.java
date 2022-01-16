package com.zuber.organizeit.domain;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.*;


@MappedSuperclass
public abstract class BaseAggregateRoot<Id extends Serializable> extends BaseEntity<Id> {

    private final @Transient
    List<Object> domainEvents = new ArrayList<>();

    protected void registerEvent(@NotNull Object event) {
        domainEvents.add(Objects.requireNonNull(event));
    }

    @AfterDomainEventPublication
    protected void clearDomainEvents() {
        this.domainEvents.clear();
    }

    @DomainEvents
    protected Collection<Object> domainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }
}
