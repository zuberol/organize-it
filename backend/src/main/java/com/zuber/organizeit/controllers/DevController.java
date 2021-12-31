package com.zuber.organizeit.controllers;

import com.zuber.organizeit.Model.MultipartTest.RefFileMetadata;
import com.zuber.organizeit.Model.Note.Flashcard.Deck;
import com.zuber.organizeit.Model.Note.Flashcard.Flashcard;
import com.zuber.organizeit.Model.Note.ReferenceResource.BookReference;
import com.zuber.organizeit.Model.Note.ReferenceResource.ReferenceResource;
import com.zuber.organizeit.Model.Note.ReferenceResource.VideoReference;
import com.zuber.organizeit.Model.Repository.*;
import com.zuber.organizeit.Model.Task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
        bookReference.setResId(30L);

        VideoReference videoReference = new VideoReference();
        videoReference.setReferenceUrl("https://www.youtube.com/watch?v=av0y5TAItyk&ab_channel=JWPCREW");
        videoReference.setResId(31L);

        VideoReference videoReference2 = new VideoReference();
        videoReference2.setReferenceUrl("https://www.youtube.com/watch?v=kpUWMl0gLEQ&ab_channel=kkrawczykOnVEVO");
        videoReference2.setResId(32L);

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
        videoReference3.setResId(33L);
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

    @GetMapping("/save/project")
    public void saveProject() {
//        Task rootTask = new Task(
//                null, "some root", false, null,
//                new LinkedList<>(List.of()),
//                new LinkedList<>(List.of(new Tag(null, new LinkedList<>(List.of("hehe")))))
//        );
//        Task sub1 = new Task(
//                null, "some sub2", false, null,
//                new LinkedList<>(List.of()),
//                new LinkedList<>(List.of(new Tag(null, new LinkedList<>(List.of("heh sub1 sub1 sub1 ve")))))
//        );
//        Task sub2 = new Task(
//                null, "some sub2", false, null,
//                new LinkedList<>(List.of()),
//                new LinkedList<>(List.of(new Tag(null, new LinkedList<>(List.of("hehs  sub2 ub2e")))))
//        );
//
//        rootTask.getSubTasks().add(sub1);
//        rootTask.getSubTasks().add(sub2);
//        sub1.setParentTask(rootTask);
//        sub2.setParentTask(rootTask);
//
//        projectsRepository.save(new Project(
//                null,
//                "with subTasks",
//                rootTask,
//                "some with subtasks",
//                new LinkedList<>()
//        ));
    }




//    @GetMapping("/devDTO/rootTask")
//    Task testDevDTOdummyRootTask() {
//        return DevDTO.getRootTask();
//    }
//
//    @GetMapping("/devDTO/projects")
//    List<Project> testDevDTODummyProjectTest() {
//        return List.of(
//                Project.builder()
//                        .name("Dummy")
//                        .rootTask(DevDTO.getRootTask())
//                .build()
//        );
//    }

}
