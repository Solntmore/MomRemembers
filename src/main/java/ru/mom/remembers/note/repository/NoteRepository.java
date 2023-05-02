package ru.mom.remembers.note.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mom.remembers.note.model.Note;

import java.time.LocalDateTime;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    Page<Note> findNoteByNameNotNullOrderByNameDesc(Pageable pageable);

    Page<Note> findNoteByNameNotNullOrderByLastUpdateDateAsc(Pageable pageable);

    Page<Note> findNoteByNameNotNullOrderByLastUpdateDateDesc(Pageable pageable);

    Page<Note> findNoteByLastUpdateDateAfterAndLastUpdateDateBeforeOrderByLastUpdateDateAsc(LocalDateTime rangeStart,
                                                                                            LocalDateTime rangeEnd,
                                                                                            Pageable pageable);

    Page<Note> findByNameContainsIgnoreCaseOrAndDescriptionContainsIgnoreCase(String firstQuery, String secondQuery,
                                                                              Pageable pageable);

    Page<Note> findNoteByNameNotNullOrderByNameAsc(Pageable pageable);

}
