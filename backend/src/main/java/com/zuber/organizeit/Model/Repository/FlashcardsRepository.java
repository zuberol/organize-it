package com.zuber.organizeit.Model.Repository;

import com.zuber.organizeit.Model.Flashcard.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardsRepository extends JpaRepository<Flashcard, Long> {

}
