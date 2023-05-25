package ru.mom.remembers.note.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteFilter {

    private LocalDateTime start;

    private LocalDateTime end;

    private String text;

    private String userLogin;
}
