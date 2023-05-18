package ru.mom.remembers.note.jpa;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.mom.remembers.note.model.Note;

import java.util.Optional;

public interface NotePersistService {

    Optional<Note> getNote(Long id, String userLogin);

    Note createNote(Note note);

    Note updateNote(Note note);

    void deleteNote(Long id);

    boolean existNote(Long id);

    Page<Note> findAll(BooleanBuilder parameters, Pageable pageable);
}
