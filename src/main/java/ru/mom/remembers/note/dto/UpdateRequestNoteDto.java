package ru.mom.remembers.note.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRequestNoteDto {

    @NotNull
    private Long id;

    @Length(min = 10, max = 20)
    @NotNull
    private String name;

    @Length(max = 200)
    @NotNull
    private String description;

    private String location;

    private String season;

    private LocalDateTime created;

    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
    }
}
