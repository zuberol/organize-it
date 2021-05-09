package com.zuber.organizeit.controllers;


import com.zuber.organizeit.Model.*;
import com.zuber.organizeit.Model.Repository.DecksRepository;
import com.zuber.organizeit.Model.Repository.FlashcardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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







    @PostMapping(value = "/flashcard", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void postFileInJson(@RequestPart("flashcard") Flashcard flashcard, @RequestPart("ref_files") RefFileMetadata ref_files) {
        //todo
        if(flashcard.getFcId() == null) flashcard.setFcId(flashcardsRepository.getIdFromSeq());
        flashcardsRepository.save(flashcard);
    }








    private void saveUploadedFile(MultipartFile file) throws IOException {
        final String UPLOADED_FOLDER = "/home/jakub/IdeaProjects/personal/organize-it-files";

        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        }
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
