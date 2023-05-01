package ru.mom.remembers.note.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mom.remembers.note.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
