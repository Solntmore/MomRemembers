package ru.mom.remembers.note.jpa;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.mom.remembers.note.model.Note;
import ru.mom.remembers.note.repository.NoteRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotePersistServiceImpl implements NotePersistService {

    private final NoteRepository noteRepository;

    @Override
    public Optional<Note> getNote(Long id, String userLogin) {
        log.info("getting note by id: {}", id);
        return noteRepository.findByIdAndUserLogin(id, userLogin);
    }

    @Override
    @Transactional
    public Note createNote(Note note) {
        log.info("creating note {}", note);
        return noteRepository.save(note);
    }

    @Override
    @Transactional
    public Note updateNote(Note note) {
        log.info("updating note: {}", note);
        return noteRepository.save(note);
    }

    @Override
    @Transactional

    public void deleteNote(Long id) {
        log.info("deleting note by id: {}", id);
        noteRepository.deleteById(id);
    }

    @Override
    public boolean existNote(Long id) {
        return noteRepository.existsById(id);
    }

    @Override
    public Page<Note> findAll(BooleanBuilder parameters, Pageable pageable) {
        return noteRepository.findAll(parameters, pageable);
    }

}

