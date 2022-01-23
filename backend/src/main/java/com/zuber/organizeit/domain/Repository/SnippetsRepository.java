package com.zuber.organizeit.domain.Repository;

import com.zuber.organizeit.domain.Note.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnippetsRepository extends JpaRepository<Snippet, Long> {

    @Query(nativeQuery = true, value = "select * from snippets s inner join snippets_tags st on s.id = st.sid inner join tags t on st.tid = t.id  where t.main_name like :tagPattern")
    List<Snippet> findByTag(@Param("tagPattern") String tagPattern);

}
