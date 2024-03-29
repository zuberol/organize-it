package com.zuber.organizeit.domain.Repository;

import com.zuber.organizeit.domain.Note.Flashcard.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FlashcardsRepository extends JpaRepository<Flashcard, Long> {

    @Query(nativeQuery = true, value = """
            SELECT * FROM FLASHCARDS f
            left join FLASHCARDS_TAGS ft on f.id = ft.flashcard_id
            left join tags t on ft.tags_tag_id = t.tag_id where t.main_name in :tags""")
    List<Flashcard> getRandomFlashcards(@Param("tags") Collection<String> tags);

}
