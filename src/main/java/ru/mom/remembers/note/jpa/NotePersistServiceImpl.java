package ru.mom.remembers.note.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mom.remembers.note.model.Note;
import ru.mom.remembers.note.repository.NoteRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NotePersistServiceImpl implements NotePersistService {

    private final NoteRepository noteRepository;

    @Override
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    @Transactional
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    @Transactional
    public Note updateNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    @Transactional
    public void deleteNote(Long id) {

    }
}
