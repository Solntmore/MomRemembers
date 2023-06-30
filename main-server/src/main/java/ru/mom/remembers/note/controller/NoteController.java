package ru.mom.remembers.note.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.mom.remembers.note.dto.FullResponseNoteDto;
import ru.mom.remembers.note.dto.NewRequestNoteDto;
import ru.mom.remembers.note.dto.ShortResponseNoteDto;
import ru.mom.remembers.note.dto.UpdateRequestNoteDto;
import ru.mom.remembers.note.model.SortedKeys;
import ru.mom.remembers.note.service.NoteService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/notes")
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/{id}")
    public ResponseEntity<FullResponseNoteDto> getNote(@PathVariable Long id) {

        var userLogin = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("Get a note by id = " + id);
        return ResponseEntity.status(HttpStatus.OK).body(noteService.getNote(id, userLogin));
    }

    @PostMapping()
    public ResponseEntity<FullResponseNoteDto> createNote(@RequestBody NewRequestNoteDto newNoteDto) {

        var userLogin = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("Request to create a note.");
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNote(newNoteDto, userLogin));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FullResponseNoteDto> updateNote(@RequestBody @Valid UpdateRequestNoteDto updateNoteDto,
                                                          @PathVariable Long id) {

        var userLogin = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("Request to update a note.");
        return ResponseEntity.status(HttpStatus.OK).body(noteService.updateNote(updateNoteDto, id, userLogin));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable Long id) {

        var userLogin = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("Request to delete a note.");
        noteService.deleteNote(id, userLogin);
    }

    @GetMapping
    public ResponseEntity<List<ShortResponseNoteDto>> getNotes(
            @RequestParam(required = false) String rangeStart,
            @RequestParam(required = false) String rangeEnd,
            @RequestParam(name = "text", required = false) String query,
            @RequestParam(required = false, defaultValue = "0") int from,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false,
                    defaultValue = "SORT_BY_DATE_DESC") SortedKeys sort) {
        log.debug("Request to search notes.");

        var userLogin = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.status(HttpStatus.OK).body(noteService.getNotes(userLogin, query, from, size, sort,
                rangeStart, rangeEnd));
    }
}
