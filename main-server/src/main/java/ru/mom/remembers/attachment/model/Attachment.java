package ru.mom.remembers.attachment.model;

import lombok.*;

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

    @Lob
    @Column(name = "data", columnDefinition="BLOB")
    private byte[] data;

    public Attachment() {
    }

    public Attachment(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

}
