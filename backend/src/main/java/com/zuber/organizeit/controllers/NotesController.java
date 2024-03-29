package com.zuber.organizeit.controllers;


import com.zuber.organizeit.domain.Note.Flashcard.Deck;
import com.zuber.organizeit.domain.Note.Flashcard.Flashcard;
import com.zuber.organizeit.domain.Note.NotesService;
import com.zuber.organizeit.domain.Note.SnippetTO;
import com.zuber.organizeit.domain.Repository.DecksRepository;
import com.zuber.organizeit.domain.Repository.EntityDAO;
import com.zuber.organizeit.domain.Repository.FlashcardsRepository;
import com.zuber.organizeit.domain.Repository.RefResourcesRepository;
import com.zuber.organizeit.domain.Note.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://85.222.6.237:3000"})
@RequestMapping("/api")
public class NotesController {
    private final FlashcardsRepository flashcardsRepository;
    private final DecksRepository decksRepository;
    private final RefResourcesRepository refResourcesRepository;
    private final EntityDAO entityDAO;
    private final NotesService notesService;

    @Autowired
    public NotesController(FlashcardsRepository flashcardsRepository, DecksRepository decksRepository, RefResourcesRepository refResourcesRepository, EntityDAO entityDAO, NotesService notesService) {
        this.flashcardsRepository = flashcardsRepository;
        this.decksRepository = decksRepository;
        this.refResourcesRepository = refResourcesRepository;
        this.entityDAO = entityDAO;
        this.notesService = notesService;
    }

    @GetMapping(value = "/deck", produces = APPLICATION_JSON_VALUE)
    public Deck getDeck(@RequestParam Long deckId) {
        Deck deck = decksRepository.getById(deckId);
        Collections.shuffle(deck.getFlashcards());
        return deck;
    }

    @GetMapping("/decks")
    public List<Deck> getDecks() {
        return decksRepository.findAll();
    }

 // todo deleted id sequences
//    @PostMapping(value = "/flashcard", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public void saveFlashcardWithRefs(
//            @RequestPart("flashcard") Flashcard flashcard,
//            @ModelAttribute RefFileMetadata metadata,
//            Long deckId)  {
//
//        //save ref images
//        for(int i=0; i<metadata.getRefResourceAssociatedFiles().size(); ++i) {
//            MultipartFile file = metadata.getRefResourceAssociatedFiles().get(i);
//            if (!file.isEmpty()) {
//                Path path = Paths.get(UPLOADED_FOLDER_PATH, file.getOriginalFilename());
//                try {
//                    Files.write(path, file.getBytes());
//                    final ReferenceResource referenceResource = flashcard.getReferenceResources().get(Math.toIntExact(metadata.getRefResourceIndex().get(i)));
//                    if(referenceResource instanceof ImageReference) {
//                        ImageReference imageRef = (ImageReference) referenceResource;
//                        imageRef.setImageUri(path.toString());
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        //save ref resources
//        flashcard.setReferenceResources(
//                flashcard.getReferenceResources().stream().peek(ref -> {
//                    ref.setId(referenceResourcesRepository.getIdFromSeq());
//                    referenceResourcesRepository.save(ref);
//                }).collect(Collectors.toList())
//        );
//
//        //save flashcard
//        flashcard.setFcId(flashcardsRepository.getIdFromSeq());
//        flashcardsRepository.save(flashcard);
//
//        //save flashcard in deck
//        decksRepository.findById(deckId).ifPresent(
//                deck -> {
//                    deck.getFlashcards().add(flashcard);
//                    decksRepository.save(deck);
//                }
//        );
//
//    }

    @GetMapping(value = "/flashcard")
    public Flashcard getFlashcard(@RequestParam Long id) {
        return flashcardsRepository.findById(id).orElseThrow();
    }

    @GetMapping(value = "/flashcard/random")
    public List<Flashcard> getRandomFlashcards(@RequestParam String [] tags) {
        return entityDAO.getRandomFlashcards(tags);
    }

    @GetMapping("/snippets")
    public List<Snippet> findSnippetByTag(@RequestParam(required = false) String [] tags) {
        return tags != null ? entityDAO.findByTags(tags) : entityDAO.findAllSnippets();
    }

    @PostMapping("/snippet")
    public ResponseEntity<Snippet> modifySnippet(@RequestBody SnippetTO snippetTO) {
        return ResponseEntity.of(notesService.newScratchSnippet(snippetTO));
    }


}
