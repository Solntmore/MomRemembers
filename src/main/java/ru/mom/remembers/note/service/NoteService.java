package ru.mom.remembers.note.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    List<ShortResponseNoteDto> getNotes(String query, Pageable pageable, SortedKeys sort);

    List<ShortResponseNoteDto> getNotesBetweenDates(String rangeStart, String rangeEnd, PageRequest page);

}



