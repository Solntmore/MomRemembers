package ru.mom.remembers.note.service;

import ru.mom.remembers.note.dto.FullResponseNoteDto;
import ru.mom.remembers.note.dto.NewRequestNoteDto;
import ru.mom.remembers.note.dto.ShortResponseNoteDto;
import ru.mom.remembers.note.dto.UpdateRequestNoteDto;
import ru.mom.remembers.note.model.SortedKeys;

import java.util.List;

public interface NoteService {

    FullResponseNoteDto getNote(Long id);

    FullResponseNoteDto createNote(NewRequestNoteDto newNoteDto);

    FullResponseNoteDto updateNote(UpdateRequestNoteDto updateNoteDto, Long id);

    void deleteNote(Long id);

    List<ShortResponseNoteDto> getNotes(String query, int from, int size, SortedKeys sort,
                                        String rangeStart, String rangeEnd);

}



