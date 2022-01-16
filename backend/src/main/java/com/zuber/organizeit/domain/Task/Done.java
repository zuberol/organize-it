package com.zuber.organizeit.domain.Task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.zuber.organizeit.utils.Utils.not;

@Embeddable
@Builder
@Getter
@AllArgsConstructor
public class Done {

    @Column(name = "whenDone")
    LocalDateTime when;

    protected Done() {

    }

    public boolean isDone() {
        return not(Objects.isNull(when));
    }
}
