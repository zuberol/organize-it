package com.zuber.organizeit.controllers;

import com.zuber.organizeit.Model.Flashcard.Flashcard;
import com.zuber.organizeit.Model.Repository.FlashcardsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {FlashcardsController.class})
class FlashcardsControllerTest {


//    @MockBean FlashcardsRepository flashcardsRepository;
//    @MockBean DecksRepository decksRepository;
//    @MockBean ReferenceResourcesRepository referenceResourcesRepository;

//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void getDeck() {
        FlashcardsRepository flashcardsRepository = mock(FlashcardsRepository.class);
        assertNotNull(flashcardsRepository.findAll());
    }

    @Test
    void getDecks() {
        FlashcardsRepository flashcardsRepository = mock(FlashcardsRepository.class);
        Flashcard flashcard = flashcardsRepository.getOne(1L);
        assertNotNull(flashcard.toString());
        assertDoesNotThrow(() -> new EntityNotFoundException("the flashcard with id 1 was not found"));

    }
//
//    @Test
//    void saveFlashcardWithRefs() {
//    }
//
//    @Test
//    void getFlashcard() {
//    }
}