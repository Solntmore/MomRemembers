package ru.mom.remembers.note.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRequestNoteDto {

    @NotNull
    private Long id;

    @NotBlank
    @Length(min = 1, max = 50)
    private String name;

    @Length(max = 200)
    private String description;

    @NotBlank
    @Length(min = 1, max = 200)
    private String location;

    @Length(max = 200)
    private String season;

    private LocalDateTime created;

    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
    }
}
