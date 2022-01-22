package com.zuber.organizeit.domain.Repository;

import com.zuber.organizeit.domain.Note.Flashcard.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DecksRepository extends JpaRepository<Deck, Long> {

//    Optional<Deck> findByTitle(String title);

}
