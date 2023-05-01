package ru.mom.remembers.note.jpa;


import ru.mom.remembers.note.model.Note;

import java.util.Optional;

public interface NotePersistService {

    Optional<Note> getNoteById(Long id);

    Note createNote(Note note);

    Note updateNote(Note note);

    void deleteNote(Long id);
}
