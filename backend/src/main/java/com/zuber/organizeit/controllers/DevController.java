package com.zuber.organizeit.controllers;

import com.zuber.organizeit.Model.*;
import com.zuber.organizeit.Model.BookReference;
import com.zuber.organizeit.Model.ReferenceResource;
import com.zuber.organizeit.Model.Repository.TaskRepository;
import com.zuber.organizeit.Model.VideoReference;
import com.zuber.organizeit.Model.Repository.DecksRepository;
import com.zuber.organizeit.Model.Repository.FlashcardsRepository;
import com.zuber.organizeit.Model.Repository.ReferenceResourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://85.222.6.237:3000"})
@RequestMapping("/api/dev")
public class DevController {

    FlashcardsRepository flashcardsRepository;
    DecksRepository decksRepository;
    ReferenceResourcesRepository referenceResourcesRepository;
    TaskRepository taskRepository;

    @Autowired
    public DevController(FlashcardsRepository flashcardsRepository, DecksRepository decksRepository, ReferenceResourcesRepository referenceResourcesRepository, TaskRepository taskRepository) {
        this.flashcardsRepository = flashcardsRepository;
        this.decksRepository = decksRepository;
        this.referenceResourcesRepository = referenceResourcesRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/root")
    public Task getRootTask(@RequestParam Long id) {
        return taskRepository.findById(id).get();
    }


    @GetMapping("/snake")
    public List<Deck> ifSnakeCaseWorks() {
        return decksRepository.findAll();
    }

    @GetMapping("/refsource")
    public ReferenceResource getReferenceResourceOne() {
        return new BookReference();
    }

    @GetMapping("/refsourceSave")
    public void saveRefResource() {
        BookReference bookReference = new BookReference();
        bookReference.setAuthor("Hemingway");
        bookReference.setId(30L);

        VideoReference videoReference = new VideoReference();
        videoReference.setReferenceUrl("https://www.youtube.com/watch?v=av0y5TAItyk&ab_channel=JWPCREW");
        videoReference.setId(31L);

        VideoReference videoReference2 = new VideoReference();
        videoReference2.setReferenceUrl("https://www.youtube.com/watch?v=kpUWMl0gLEQ&ab_channel=kkrawczykOnVEVO");
        videoReference2.setId(32L);

        //to nie zadziala bo nie mozna zapisac, chyba ze sie zrobi castowanie to zadziala moze
        //to nastepne jest skastowane i zadziala
//        Object obj = videoReference2;
//        var hehehehe = obj;

        // kastowanie w gore
        Object obj = videoReference2;
        var castowaneiObject = obj;

        // moze to
        VideoReference videoReference3 = new VideoReference();
        videoReference3.setReferenceUrl("https://www.baeldung.com/hibernate-inheritance");
        videoReference3.setId(33L);
        Object ob = videoReference3;
        var someVar = (ReferenceResource) ob;


        // ale to tak
        var hehehehe = videoReference2;


        referenceResourcesRepository.save(bookReference);
        referenceResourcesRepository.save(videoReference);
        referenceResourcesRepository.save(hehehehe);
        referenceResourcesRepository.save(someVar);
        referenceResourcesRepository.save((ReferenceResource) castowaneiObject);

    }

    //todo dev3 wrap all into dto, why not? - rozpruc flashcard object na froncie i dodawac jako form.
    @PostMapping(value = "/filecheck", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void postFileInJson(@RequestPart("flashcard") Flashcard flashcard, @ModelAttribute RefFileMetadata metadata) {

    }



}
