package com.zuber.organizeit.Model.Repository;

import com.zuber.organizeit.Model.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DecksRepository extends JpaRepository<Deck, Long> {

    @Query(value = "SELECT nextval('" + Deck.ID_SEQ_NAME + "')", nativeQuery = true)
    Long getIdFromSeq();

}
