package com.zuber.organizeit.Model.Repository;

import com.zuber.organizeit.Model.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DecksRepository extends JpaRepository<Deck, Long> {

    Optional<Deck> findByTitle(String title);

}
