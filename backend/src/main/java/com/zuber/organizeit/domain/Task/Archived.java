package com.zuber.organizeit.domain.Task;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.zuber.organizeit.utils.Utils.not;

@Embeddable
@Getter
public class Archived {


    @Column(name = "whenArchived")
    LocalDateTime when;

    public boolean isArchived() {
        return not(Objects.isNull(when));
    }
}
