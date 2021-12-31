package com.zuber.organizeit.services.exporters;

import com.zuber.organizeit.Model.Note.Flashcard.Deck;
import com.zuber.organizeit.Model.Repository.EntityDAO;
import com.zuber.organizeit.services.exporters.old.FlashcardParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.Collection;

@Service
public class FlashcardExporterS {

    private final EntityDAO entityDAO;

    @Autowired
    public FlashcardExporterS(EntityDAO entityDAO) {
        this.entityDAO = entityDAO;
    }

    public void initDb(Collection<Path> deckDirs) {

        deckDirs.stream()
                .map(deckPath -> {
                    Deck deck = new Deck();
                    deck.setTitle(deckPath.getFileName().toString());
                    deck.setFlashcards(FlashcardParser.parse(deckDirs.iterator().next()));
                    return deck;
                })
                .forEach(entityDAO::save);

    }
}
