package ru.mom.remembers.note.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.mom.remembers.note.model.Note;
import ru.mom.remembers.note.repository.NoteRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotePersistServiceImpl implements NotePersistService {

    private final NoteRepository noteRepository;

    @Override
    public Optional<Note> getNote(Long id) {
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
        noteRepository.deleteById(id);
    }

    @Override
    public boolean existNote(Long id) {
        return noteRepository.existsById(id);
    }

    @Override
    public Page<Note> getNotesByWords(String query, Pageable pageable) {
        return noteRepository.findByNameContainsIgnoreCaseOrAndDescriptionContainsIgnoreCase(query, query, pageable);
    }

    @Override
    public Page<Note> getNotesByAlphabetAsc(Pageable pageable) {
        return noteRepository.findNoteByNameNotNullOrderByNameAsc(pageable);
    }

    @Override
    public Page<Note> getNotesByAlphabetDesc(Pageable pageable) {
        return noteRepository.findNoteByNameNotNullOrderByNameDesc(pageable);
    }

    @Override
    public Page<Note> getNotesByCreatedAsc(Pageable pageable) {
        return noteRepository.findNoteByNameNotNullOrderByLastUpdateDateAsc(pageable);
    }

    @Override
    public Page<Note> getNotesByCreatedDesc(Pageable pageable) {
        return noteRepository.findNoteByNameNotNullOrderByLastUpdateDateDesc(pageable);
    }

    @Override
    public Page<Note> getNotesBetweenDates(LocalDateTime rangeStart, LocalDateTime rangeEnd, PageRequest page) {
        return noteRepository.findNoteByLastUpdateDateAfterAndLastUpdateDateBeforeOrderByLastUpdateDateAsc(rangeStart, rangeEnd, page);
    }

}
