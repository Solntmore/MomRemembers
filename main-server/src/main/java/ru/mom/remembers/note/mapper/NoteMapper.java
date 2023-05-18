package ru.mom.remembers.note.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.mom.remembers.note.dto.FullResponseNoteDto;
import ru.mom.remembers.note.dto.NewRequestNoteDto;
import ru.mom.remembers.note.dto.ShortResponseNoteDto;
import ru.mom.remembers.note.dto.UpdateRequestNoteDto;
import ru.mom.remembers.note.model.Note;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NoteMapper {

    @Mapping(target = "id", source = "note.id")
    @Mapping(target = "userLogin", source = "userLogin")
    FullResponseNoteDto toFullNote(Note note, String userLogin);

    @Mapping(target = "id", source = "note.id")
    FullResponseNoteDto toFullNote(Note note);

    @Mapping(target = "userLogin", source = "userLogin")
    Note toNote(NewRequestNoteDto newNoteDto, String userLogin);

    ShortResponseNoteDto toShortNote(Note note);

    @Mapping(target = "lastUpdateDate", ignore = true)
    void mergeToNote(UpdateRequestNoteDto updateEventDto, @MappingTarget Note note);
}