package ru.mom.remembers.note.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.mom.remembers.note.dto.FullResponseNoteDto;
import ru.mom.remembers.note.dto.NewRequestNoteDto;
import ru.mom.remembers.note.dto.UpdateRequestNoteDto;
import ru.mom.remembers.note.service.NoteService;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(path = "/notes")
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/{id}")
    public ResponseEntity<FullResponseNoteDto> getNote(@PathVariable Long id) {

        log.info("Get a note by id = " + id);
        return ResponseEntity.status(HttpStatus.OK).body(noteService.getNote(id));
    }

    @PostMapping()
    public ResponseEntity<FullResponseNoteDto> createNote(@RequestBody NewRequestNoteDto newNoteDto) {

        log.info("Request to create a note.");
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNote(newNoteDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FullResponseNoteDto> updateNote(@RequestBody UpdateRequestNoteDto updateNoteDto,
                                                          @PathVariable Long id) {

        log.info("Request to update a note.");
        return ResponseEntity.status(HttpStatus.OK).body(noteService.updateNote(updateNoteDto, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable Long id) {

        log.info("Request to delete a note.");
        noteService.deleteNote(id);
    }


}
