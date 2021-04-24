package com.zuber.organizeit.controllers;

import com.zuber.organizeit.Model.*;
import com.zuber.organizeit.Model.Repository.DecksRepository;
import com.zuber.organizeit.Model.Repository.FlashcardsRepository;
import com.zuber.organizeit.Model.Repository.ImageRefResource;
import com.zuber.organizeit.Model.Repository.ReferenceResourcesRepository;
import com.zuber.organizeit.Model.tests.OuterTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://85.222.6.237:3000"})
@RequestMapping("/api/dev")
public class DevController {

    FlashcardsRepository flashcardsRepository;
    DecksRepository decksRepository;
    ReferenceResourcesRepository referenceResourcesRepository;

    @Autowired
    public DevController(FlashcardsRepository flashcardsRepository, DecksRepository decksRepository, ReferenceResourcesRepository referenceResourcesRepository) {
        this.flashcardsRepository = flashcardsRepository;
        this.decksRepository = decksRepository;
        this.referenceResourcesRepository = referenceResourcesRepository;
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
    public void saveRefResource() throws MalformedURLException {
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


//    @PostMapping("/refsourceSave")
//    public <T extends ReferenceResource> void saveRefResourceFromFrontend(T ref) {
////        System.out.println(ref.getClass().getName());
//
//        System.out.println(((BookReference)ref).getAuthor());
//        if(ref instanceof BookReference) System.out.println("to jest ksiazka: " + (((BookReference)ref).getAuthor())   );
//        if(ref instanceof VideoReference) System.out.println("to jest video: " + (((VideoReference)ref).getReferenceUrl().getRef())   );
//
//    }

    //@JsonTypeResolver()


    // todo dzialalo 1
    @PostMapping(value = "/resources", consumes = "application/json")
    public void saveRefResourceFromFrontend(@RequestBody List<ReferenceResource> ref) {
//        System.out.println(ref.getClass().getName());
        System.out.println("klasa pod referencja: " + ref);
        ref.forEach(item -> {
            if (item instanceof BookReference)
                System.out.println("to jest ksiazka: " + (((BookReference) item).getAuthor()));
            if (item instanceof VideoReference)
                System.out.println("to jest video: " + (((VideoReference) item).getReferenceUrl()));
            item.setId(
                    referenceResourcesRepository.getIdFromSeq()
            );
            referenceResourcesRepository.save(item);
        });
    }


    @GetMapping(value = "/ref/resources")
    public List<ReferenceResource> getReferenceSourcessss() throws MalformedURLException {

        BookReference bookReference = new BookReference();
        bookReference.setAuthor("Harry Potter i komnata wpierdolu");

        VideoReference videoReference = new VideoReference();
        videoReference.setReferenceUrl("https://www.youtube.com/watch?v=av0y5TAItyk&ab_channel=JWPCREW");

        return new ArrayList<>(List.of(videoReference, bookReference));
    }


    @GetMapping(value = "/resources/all")
    public List<ReferenceResource> getReferenceResourcesAll() {

        ImageRefResource imageRefResource = new ImageRefResource();
        imageRefResource.setRefImage("https://img-9gag-fun.9cache.com/photo/a5WnX5o_700b.jpg");
        imageRefResource.setId(referenceResourcesRepository.getIdFromSeq());

        VideoReference videoReference = new VideoReference();
        videoReference.setReferenceUrl("https://www.youtube.com/watch?v=rbIfdWnTMNE&ab_channel=AsfaltRecords");
        videoReference.setId(referenceResourcesRepository.getIdFromSeq());

        BookReference bookReference = new BookReference();
        bookReference.setAuthor("Thomas H. Cormen");
        bookReference.setId(referenceResourcesRepository.getIdFromSeq());

        referenceResourcesRepository.saveAll(
                List.of(
                        imageRefResource,
                        videoReference,
                        bookReference
                )
        );


        return referenceResourcesRepository.findAll();
    }

    // todo testing
    @PostMapping(value = "/multipart", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void ifmultipartWorks(OuterTest test) {

    }


    @PostMapping(value = "/file", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void postFileInJson(@RequestPart("flash") Flashcard flash, @RequestPart("file") MultipartFile file) {

    }



}
