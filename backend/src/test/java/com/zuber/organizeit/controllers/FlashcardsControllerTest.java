package com.zuber.organizeit.controllers;

import com.zuber.organizeit.Model.Flashcard;
import com.zuber.organizeit.Model.Repository.DecksRepository;
import com.zuber.organizeit.Model.Repository.FlashcardsRepository;
import com.zuber.organizeit.Model.Repository.ReferenceResourcesRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

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