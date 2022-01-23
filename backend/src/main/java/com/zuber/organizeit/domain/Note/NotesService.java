package com.zuber.organizeit.domain.Note;

import com.zuber.organizeit.domain.DomainService;
import com.zuber.organizeit.domain.Repository.NotesRepository;
import com.zuber.organizeit.domain.Repository.SnippetsRepository;
import com.zuber.organizeit.domain.Repository.TagsRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class NotesService implements DomainService {

    private final SnippetsRepository snippetsRepository;
    private final NotesRepository notesRepository;
    private final TagsRepository tagsRepository;


    public NotesService(SnippetsRepository snippetsRepository, NotesRepository notesRepository, TagsRepository tagsRepository) {
        this.snippetsRepository = snippetsRepository;
        this.notesRepository = notesRepository;
        this.tagsRepository = tagsRepository;
    }

    public Optional<Snippet> newScratchSnippet(SnippetTO snippetTO) {
        if(snippetTO == null) return Optional.empty();
        var snippet = ofNullable(snippetTO.getId())
                .flatMap(snippetsRepository::findById)
                .orElse(Snippet.builder().build());

        ofNullable(snippetTO.getName()).ifPresent(snippet::setName);
        ofNullable(snippetTO.getContent()).ifPresent(snippet::setContent);
        ofNullable(snippetTO.getTags())
                .flatMap(tagsRepository::findAllByMainNameIn)
                .ifPresent(snippet::setTags);

        var note = ofNullable(snippetTO.getNoteId())
                .flatMap(notesRepository::findById)
                .orElseGet(this::newScratchNote);
        if(note.getSnippets() == null) note.setSnippets(new LinkedList<>());
        note.getSnippets().add(snippet);
        notesRepository.save(note);
        return Optional.of(snippet);
    }

    private Note newScratchNote() {
        final String SCRATCH_NOTE = "Scratch note";
        return Note.builder().name(SCRATCH_NOTE).build();
    }

}
