package com.zuber.organizeit.controllers;


import com.zuber.organizeit.Model.Note.Flashcard.Deck;
import com.zuber.organizeit.Model.Note.Flashcard.Flashcard;
import com.zuber.organizeit.Model.Repository.DecksRepository;
import com.zuber.organizeit.Model.Repository.EntityDAO;
import com.zuber.organizeit.Model.Repository.FlashcardsRepository;
import com.zuber.organizeit.Model.Repository.ReferenceResourcesRepository;
import com.zuber.organizeit.Model.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://85.222.6.237:3000"})
@RequestMapping("/api")
public class FlashcardsController {
    FlashcardsRepository flashcardsRepository;
    DecksRepository decksRepository;
    ReferenceResourcesRepository referenceResourcesRepository;
    final EntityDAO entityDAO;

    @Autowired
    public FlashcardsController(FlashcardsRepository flashcardsRepository, DecksRepository decksRepository, ReferenceResourcesRepository referenceResourcesRepository, EntityDAO entityDAO) {
        this.flashcardsRepository = flashcardsRepository;
        this.decksRepository = decksRepository;
        this.referenceResourcesRepository = referenceResourcesRepository;
        this.entityDAO = entityDAO;
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
    public List<Snippet> dlnjaskjdkj() {
        return entityDAO.findByTag("scala/scala-2-snippets");
    }


}
