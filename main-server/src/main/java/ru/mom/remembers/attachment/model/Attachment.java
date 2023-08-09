package ru.mom.remembers.attachment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "attachment")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "note_id")
    private Long noteId;

    @Lob
    @Column(name = "data")                        // for Postgres
//    @Column(name = "data", columnDefinition = "BLOB") // for H2
    private byte[] data;

    public Attachment() {
    }

    public Attachment(String name, String type, Long noteId, byte[] data) {
        this.name = name;
        this.type = type;
        this.noteId = noteId;
        this.data = data;
    }

}
