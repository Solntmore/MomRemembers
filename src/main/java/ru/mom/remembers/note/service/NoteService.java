package ru.mom.remembers.note.service;

import ru.mom.remembers.note.dto.FullResponseNoteDto;
import ru.mom.remembers.note.dto.NewRequestNoteDto;
import ru.mom.remembers.note.dto.UpdateRequestNoteDto;

public interface NoteService {

    FullResponseNoteDto getFullNoteById(Long id);

    FullResponseNoteDto createNote(NewRequestNoteDto newNoteDto);

    FullResponseNoteDto updateNote(UpdateRequestNoteDto updateNoteDto, Long id);

    void deleteNote(Long id);
}



