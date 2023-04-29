package ru.mom.remembers.note.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 1, max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Length(max = 200)
    @Column(name = "description", length = 200)
    private String description = " ";

    @Length(min = 1, max = 200)
    @Column(name = "location", length = 200, nullable = false)
    private String location;

    @Length(min = 1, max = 200)
    @Column(name = "season", length = 200)
    private String season = " ";

    @Column(name = "created")
    private LocalDateTime created;

    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
    }
}
