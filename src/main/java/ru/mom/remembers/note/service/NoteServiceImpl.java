package ru.mom.remembers.note.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mom.remembers.exception.BadRequestException;
import ru.mom.remembers.exception.NotFoundException;
import ru.mom.remembers.note.dto.FullResponseNoteDto;
import ru.mom.remembers.note.dto.NewRequestNoteDto;
import ru.mom.remembers.note.dto.UpdateRequestNoteDto;
import ru.mom.remembers.note.jpa.NotePersistService;
import ru.mom.remembers.note.mapper.NoteMapper;
import ru.mom.remembers.note.model.Note;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NotePersistService notePersistService;

    private final NoteMapper noteMapper;

    @Override
    public FullResponseNoteDto getNote(Long id) {

        var note = notePersistService.getNote(id).orElseThrow(() ->
                new NotFoundException("The required object was not found.",
                        String.format("Note with id = %s was not found", id)));

        return noteMapper.toFullNote(note);
    }

    @Override
    public FullResponseNoteDto createNote(NewRequestNoteDto newNoteDto) {

        if (newNoteDto.getName() == null) {
            throw new BadRequestException("Bad request body", "Note name is empty");
        }

        if (newNoteDto.getLocation() == null) {
            throw new BadRequestException("Bad request body", "Note location is empty");
        }

        Note note = notePersistService.createNote(noteMapper.toNote(newNoteDto));

        return noteMapper.toFullNote(note);

    }

    @Override
    public FullResponseNoteDto updateNote(UpdateRequestNoteDto updateNoteDto, Long id) {

        if (!updateNoteDto.getId().equals(id)) {
            throw new BadRequestException("Bad request body", "Id of note is mistake.");
        }

        if (updateNoteDto.getName() == null) {
            throw new BadRequestException("Bad request body", "Note name is empty");
        }

        if (updateNoteDto.getLocation() == null) {
            throw new BadRequestException("Bad request body", "Note location is empty");
        }

        var note = findNote(id);

        noteMapper.mergeToNote(updateNoteDto, note);

        return noteMapper.toFullNote(notePersistService.updateNote(note));
    }

    @Override
    public void deleteNote(Long id) {

        if (!notePersistService.existNote(id)) {
            throw new NotFoundException("The required object was not found.",
                    String.format("Note with id = %s was not found", id));
        }
        notePersistService.deleteNote(id);
    }

    private Note findNote(Long id) {

        var note = notePersistService.getNote(id).orElseThrow(() ->
                new NotFoundException("The required object was not found.",
                        String.format("Note with id = %s was not found", id)));

        return note;
    }
}
