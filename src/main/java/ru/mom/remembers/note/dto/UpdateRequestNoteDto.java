package ru.mom.remembers.note.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRequestNoteDto {

    @Length(min = 10, max = 20)
    private String name;

    @Length(max = 200)
    private String description;

    private String location;

    private String season;

}
