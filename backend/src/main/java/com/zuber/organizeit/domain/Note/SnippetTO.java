package com.zuber.organizeit.domain.Note;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SnippetTO {

    private Long id;
    private String name;
    private String content;
    private Long noteId;
    private List<String> tags;

}
