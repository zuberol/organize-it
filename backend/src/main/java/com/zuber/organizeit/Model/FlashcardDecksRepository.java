package com.zuber.organizeit.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardDecksRepository extends JpaRepository<FlashcardDeck, Long> {
}
