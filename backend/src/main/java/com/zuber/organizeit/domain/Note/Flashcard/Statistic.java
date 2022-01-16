package com.zuber.organizeit.domain.Note.Flashcard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Setter @Getter
public class Statistic {

    private Long difficulty;
    private Long importanceLevel; // defaults todo
    private Long skippedTimes;
    private Long succeededAttempts;
    private Long attempts;

    public Statistic(Long importanceLevel, Long difficulty) {
        this.importanceLevel = importanceLevel;
        this.difficulty = difficulty;
    }

    public static Long toDifficulty(String difficulty) {
        long diffNumber = 0L;
        if(difficulty.matches("easy")) diffNumber = 100L;
        else if (difficulty.matches("medium")) diffNumber = 500L;
        else if(difficulty.matches("hard")) diffNumber = 1000L;
        return diffNumber;
    }


}
