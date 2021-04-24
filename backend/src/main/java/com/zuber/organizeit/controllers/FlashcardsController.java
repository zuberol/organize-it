package com.zuber.organizeit.controllers;


import com.zuber.organizeit.Model.*;
import com.zuber.organizeit.Model.Repository.DecksRepository;
import com.zuber.organizeit.Model.Repository.FlashcardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://85.222.6.237:3000"})
@RequestMapping("/api")
public class FlashcardsController {
    FlashcardsRepository flashcardsRepository;
    DecksRepository decksRepository;

    @Autowired
    public FlashcardsController(FlashcardsRepository flashcardsRepository, DecksRepository decksRepository) {
        this.flashcardsRepository = flashcardsRepository;
        this.decksRepository = decksRepository;
    }





    @GetMapping("/deck")
    public Deck getDeck(@RequestParam Long deckId) {
        return decksRepository.getOne(deckId);
    };

    @GetMapping("/decks")
    public List<Deck> getDecks() {
        return decksRepository.findAll();
    }
























    //todo
    //    @PostMapping(value = "/flashcard")
    @PostMapping(value = "/flashcard", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} )
    public Flashcard saveTest(Flashcard flashcard) {
        if(flashcard.getFcId() == null) {
            Long id = flashcardsRepository.getIdFromSeq();
            flashcard.setFcId(id);
        }
        return flashcardsRepository.save(flashcard);
    }




    @GetMapping(value = "/flashcard")
    public Flashcard getFlashcard(@RequestParam Long id) {
        return flashcardsRepository.findById(id).orElseThrow();
    }


















//
//    @PostMapping("/flashcarddeck/add")
//    public ResponseEntity<?> addFlashcardToSet(@RequestBody Flashcard flashcardToAdd, @RequestParam Long fcsetId) {
//        ResponseEntity<?> responseEntity = new ResponseEntity<>(HttpStatus.OK);
//        if (fcsetId == null || flashcardToAdd == null) responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        else if(Flashcard.isValid(flashcardToAdd)) {
//            responseEntity = new ResponseEntity<>(HttpStatus.OK);
//            Deck modifiedFlashcardDeck = DecksRepository.findById(fcsetId).orElseThrow();
//            modifiedFlashcardDeck.getFlashcards().add(flashcardToAdd);
//            DecksRepository.save(modifiedFlashcardDeck);
//        }
//        return responseEntity;
//    }



//    @PostMapping//todo test it
//    public ResponseEntity<?> modifyFlashcardSet(@RequestBody Deck requestedModificationSet) {
//        if(!Deck.isValid(requestedModificationSet)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        List<Flashcard> flashcards = flashcardSetsRepository
//                .findById(requestedModificationSet.getFcset_id())
//                .orElseGet(Deck::new).getFlashcards();
//        requestedModificationSet.getFlashcards().addAll(flashcards);
//        flashcardSetsRepository.save(requestedModificationSet);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


}
