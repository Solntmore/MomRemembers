package ru.mom.remembers.note.service;

import com.querydsl.core.BooleanBuilder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.mom.remembers.exception.BadRequestException;
import ru.mom.remembers.exception.NotFoundException;
import ru.mom.remembers.note.dto.FullResponseNoteDto;
import ru.mom.remembers.note.dto.NewRequestNoteDto;
import ru.mom.remembers.note.dto.ShortResponseNoteDto;
import ru.mom.remembers.note.dto.UpdateRequestNoteDto;
import ru.mom.remembers.note.jpa.NotePersistService;
import ru.mom.remembers.note.mapper.NoteMapper;
import ru.mom.remembers.note.model.Note;
import ru.mom.remembers.note.model.QNote;
import ru.mom.remembers.note.model.SortedKeys;
import ru.mom.remembers.note.util.NoteFilter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static ru.mom.remembers.config.CacheName.NOTE_CACHE;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteServiceImpl implements NoteService {

    private final NotePersistService notePersistService;

    private final NoteMapper noteMapper;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    @Cacheable(NOTE_CACHE)
    public FullResponseNoteDto getNote(Long id, String userLogin) {

        var note = notePersistService.getNote(id, userLogin).orElseThrow(() ->
                new NotFoundException("The required object was not found.",
                        String.format("Note with id = %s and userLogin = %s was not found.", id, userLogin)));

        return noteMapper.toFullNote(note, userLogin);
    }

    @Override
    public FullResponseNoteDto createNote(NewRequestNoteDto newNoteDto, String userLogin) {

        if (newNoteDto.getName() == null) {
            throw new BadRequestException("Bad request body", "Note name is empty");
        }

        if (newNoteDto.getLocation() == null) {
            throw new BadRequestException("Bad request body", "Note location is empty");
        }

        Note note = notePersistService.createNote(noteMapper.toNote(newNoteDto, userLogin));

        return noteMapper.toFullNote(note);

    }

    @Override
    @CachePut(cacheNames = NOTE_CACHE, key = "#id")
    public FullResponseNoteDto updateNote(UpdateRequestNoteDto updateNoteDto, Long id, String userLogin) {

        if (!updateNoteDto.getId().equals(id)) {
            throw new BadRequestException("Bad request body", "Id of note is mistake.");
        }

        if (updateNoteDto.getName() == null) {
            throw new BadRequestException("Bad request body", "Note name is empty");
        }

        if (updateNoteDto.getLocation() == null) {
            throw new BadRequestException("Bad request body", "Note location is empty");
        }

        var note = findNote(id, userLogin);

        noteMapper.mergeToNote(updateNoteDto, note);

        return noteMapper.toFullNote(notePersistService.updateNote(note), userLogin);
    }

    @Override
    @CacheEvict(cacheNames = NOTE_CACHE, key = "#id")
    public void deleteNote(Long id, String userLogin) {

        findNote(id, userLogin);

        notePersistService.deleteNote(id);
    }

    private Note findNote(Long id, String userLogin) {

        return notePersistService.getNote(id, userLogin).orElseThrow(() ->
                new NotFoundException("The required object was not found.",
                        String.format("Note with id = %s and userLogin = %s was not found.", id, userLogin)));
    }

    @Override
    public List<ShortResponseNoteDto> getNotes(String userLogin, String text, int from, int size,
                                               SortedKeys sort, String start, String end) {

        NoteFilter filter = makeFilter(userLogin, text, start, end);
        BooleanBuilder parameters = makeBooleanBuilder(filter);
        Pageable pageParameters;

        if (sort.equals(SortedKeys.SORT_BY_ALPHABET_ASC)) {
            pageParameters = PageRequest.of(from, size, Sort.by("name").ascending());
        } else if (sort.equals(SortedKeys.SORT_BY_ALPHABET_DESC)) {
            pageParameters = PageRequest.of(from, size, Sort.by("name").descending());
        } else if (sort.equals(SortedKeys.SORT_BY_DATE_ASC)) {
            pageParameters = PageRequest.of(from, size, Sort.by("lastUpdateDate").ascending());
        } else if (sort.equals(SortedKeys.SORT_BY_DATE_DESC)) {
            pageParameters = PageRequest.of(from, size, Sort.by("lastUpdateDate").descending());
        } else {
            throw new BadRequestException("The required object was not found.", "Incorrect parameters for search");
        }

        List<Note> notes = notePersistService.findAll(parameters, pageParameters).getContent();


        return notes
                .stream()
                .map(noteMapper::toShortNote)
                .collect(Collectors.toList());
    }

    @NonNull
    private BooleanBuilder makeBooleanBuilder(@NonNull NoteFilter filter) {
        BooleanBuilder builder = new BooleanBuilder();
        if (filter.getText() != null) {
            builder.and(QNote.note.name.containsIgnoreCase(filter.getText()))
                    .or(QNote.note.description.containsIgnoreCase(filter.getText()));
        }

        builder.and(QNote.note.lastUpdateDate.after(filter.getStart()));
        builder.and(QNote.note.lastUpdateDate.before(filter.getEnd()));
        builder.and(QNote.note.userLogin.eq(filter.getUserLogin()));

        return builder;
    }

    private NoteFilter makeFilter(String userLogin, String text, String rangeStart, String rangeEnd) {
        LocalDateTime start;
        LocalDateTime end;

        if (rangeStart == null) {
            start = LocalDateTime.now().minusYears(100);
            end = LocalDateTime.now().plusYears(100);
        } else {
            start = LocalDateTime.parse(rangeStart, formatter);
            end = LocalDateTime.parse(rangeEnd, formatter);
        }

        return NoteFilter.builder().text(text).start(start).end(end).userLogin(userLogin).build();
    }


}
