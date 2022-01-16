package com.zuber.organizeit.domain.Repository;

import com.zuber.organizeit.domain.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnippetsRepository extends JpaRepository<Snippet, Long> {

    @Query(nativeQuery = true, value = "select * from snippets s inner join snippets_tags st on s.snippet_id = st.snippet_snippet_id inner join tags t on st.tags_tag_id = t.tag_id  where t.main_name like :tagPattern")
    List<Snippet> findByTag(@Param("tagPattern") String tagPattern);

}
