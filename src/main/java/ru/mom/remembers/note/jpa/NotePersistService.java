package ru.mom.remembers.note.jpa;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.mom.remembers.note.model.Note;

import java.time.LocalDateTime;
import java.util.Optional;

public interface NotePersistService {

    Optional<Note> getNote(Long id);

    Note createNote(Note note);

    Note updateNote(Note note);

    void deleteNote(Long id);
    boolean existNote(Long id);

    Page<Note> getNotesByWords(String query, Pageable pageable);

    Page<Note> getNotesByAlphabetAsc(Pageable pageable);

    Page<Note> getNotesByAlphabetDesc(Pageable pageable);

    Page<Note> getNotesByCreatedAsc(Pageable pageable);

    Page<Note> getNotesByCreatedDesc(Pageable pageable);

    Page<Note> getNotesBetweenDates(LocalDateTime rangeStart, LocalDateTime rangeEnd, PageRequest page);
}
