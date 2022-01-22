package com.zuber.organizeit.domain.Note.Flashcard;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @NoArgsConstructor @AllArgsConstructor
public class DeckTO {

    @JsonAlias({"id", "deckId", "deck_id" })
    @Nullable private Long id;
    @Nullable private String name;
    @Nullable private List<Long> flashcardsIds;

}
