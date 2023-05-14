package ru.mom.remembers.note.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewRequestNoteDto {

    @NotBlank
    @Length(min = 1, max = 50)
    private String name;

    @Length(max = 200)
    private String description;

    @NotBlank
    @Length(min = 1, max = 200)
    private String location;

    @Length(max = 30)
    private String season;

}
