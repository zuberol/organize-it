package com.zuber.organizeit.Model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.persistence.Entity;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public @interface DomainEntity {
}
