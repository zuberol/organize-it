package com.zuber.organizeit.controllers;


import com.zuber.organizeit.Model.Flashcard;
import com.zuber.organizeit.Model.FlashcardDeck;
import com.zuber.organizeit.Model.FlashcardDecksRepository;
import com.zuber.organizeit.Model.FlashcardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://85.222.6.237:3000"})
@RequestMapping("/api")
public class FlashcardsController {
    FlashcardsRepository flashcardsRepository;
    FlashcardDecksRepository flashcardDecksRepository;

    @Autowired
    public FlashcardsController(FlashcardsRepository flashcardsRepository, FlashcardDecksRepository flashcardDecksRepository) {
        this.flashcardsRepository = flashcardsRepository;
        this.flashcardDecksRepository = flashcardDecksRepository;
    }

    @GetMapping("/flashcarddeck")
    public FlashcardDeck getFlashcardDeck(@RequestParam Long deckId) {
        return flashcardDecksRepository.getOne(deckId);
    };

    @GetMapping("/flashcarddecks")
    public List<FlashcardDeck> getFlashcardDecks() {
        return flashcardDecksRepository.findAll();
    }

    public ResponseEntity<?> saveFlashcard(@RequestBody Flashcard flashcard) {
        flashcardsRepository.save(flashcard);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/flashcarddeck/add")
    public ResponseEntity<?> addFlashcardToSet(@RequestBody Flashcard flashcardToAdd, @RequestParam Long fcsetId) {
        ResponseEntity<?> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        if (fcsetId == null || flashcardToAdd == null) responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else if(Flashcard.isValid(flashcardToAdd)) {
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
            FlashcardDeck modifiedFlashcardDeck = flashcardDecksRepository.findById(fcsetId).orElseThrow();
            modifiedFlashcardDeck.getFlashcards().add(flashcardToAdd);
            flashcardDecksRepository.save(modifiedFlashcardDeck);
        }
        return responseEntity;
    }



//    @PostMapping//todo test it
//    public ResponseEntity<?> modifyFlashcardSet(@RequestBody FlashcardDeck requestedModificationSet) {
//        if(!FlashcardDeck.isValid(requestedModificationSet)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        List<Flashcard> flashcards = flashcardSetsRepository
//                .findById(requestedModificationSet.getFcset_id())
//                .orElseGet(FlashcardDeck::new).getFlashcards();
//        requestedModificationSet.getFlashcards().addAll(flashcards);
//        flashcardSetsRepository.save(requestedModificationSet);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


}
