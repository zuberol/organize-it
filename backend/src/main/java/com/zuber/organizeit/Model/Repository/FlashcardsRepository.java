package com.zuber.organizeit.Model.Repository;

import com.zuber.organizeit.Model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardsRepository extends JpaRepository<Flashcard, Long> {

    @Query(value = "SELECT nextval('" + Flashcard.ID_SEQ_NAME + "')", nativeQuery = true)
    Long getIdFromSeq();

}
