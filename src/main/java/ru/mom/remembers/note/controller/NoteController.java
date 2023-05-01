package ru.mom.remembers.note.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public FullResponseNoteDto getFullNote(@PathVariable(name = "id") Long id) {

        log.info("Получение объекта по id = " + id);
        return noteService.getFullNoteById(id);
    }

    @PostMapping()
    public FullResponseNoteDto createNote(@RequestBody NewRequestNoteDto newNoteDto) {

        log.info("Запрос на создание объекта.");
        return noteService.createNote(newNoteDto);
    }

    @PutMapping("/{id}")
    public FullResponseNoteDto updateNote(@RequestBody UpdateRequestNoteDto updateNoteDto,
                                          @PathVariable(name = "id") Long id) {

        log.info("Запрос на обновление объекта.");
        return noteService.updateNote(updateNoteDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable(name = "id") Long id) {

        log.info("Запрос на удаление объекта.");
        noteService.deleteNote(id);
    }
}
