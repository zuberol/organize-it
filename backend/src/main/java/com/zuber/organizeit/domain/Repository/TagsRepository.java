package com.zuber.organizeit.domain.Repository;

import com.zuber.organizeit.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends JpaRepository<Tag, Long> {
}
