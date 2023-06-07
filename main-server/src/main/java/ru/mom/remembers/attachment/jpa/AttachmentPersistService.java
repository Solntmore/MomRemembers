package ru.mom.remembers.attachment.jpa;

import ru.mom.remembers.attachment.model.Attachment;

import java.util.Optional;

public interface AttachmentPersistService {

    Attachment store(Attachment attachment);

    Optional<Attachment> getAttachmentByName(String fileName);

    Optional<Attachment> getAttach(Long id);

}
