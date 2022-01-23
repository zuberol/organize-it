package com.zuber.organizeit.domain.Repository;

import com.zuber.organizeit.domain.Note.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<Note, Long> {
}
