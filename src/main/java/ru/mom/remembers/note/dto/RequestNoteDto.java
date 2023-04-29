package ru.mom.remembers.note.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestNoteDto {

        @Length(min = 10, max = 20)
        @NotBlank
        private String name;

        @Length(max = 200)
        private String description;

        @NotBlank
        private String location;

        private String season;

}
