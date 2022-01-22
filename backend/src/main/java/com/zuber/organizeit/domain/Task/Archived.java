package com.zuber.organizeit.domain.Task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.zuber.organizeit.utils.Utils.not;

@Embeddable
@Getter
@Builder
@AllArgsConstructor
public class Archived {

    protected Archived() {

    }

    @Column(name = "whenArchived")
    private LocalDateTime when;

    @Column(name = "isArchived", nullable = false)
    @ColumnDefault(value = "false")
    @Builder.Default
    private boolean isArchived = false;


    public boolean isArchived() {
        return not(Objects.isNull(when));
    }
}
