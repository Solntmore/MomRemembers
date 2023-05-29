package ru.mom.remembers.attachment.service;

import org.springframework.web.multipart.MultipartFile;
import ru.mom.remembers.attachment.model.Attachment;

import java.io.IOException;
import java.util.stream.Stream;

public interface AttachmentStorageService {

    public Attachment store(MultipartFile file) throws IOException;

    public Attachment getAttachment(Long id);

    public Stream<Attachment> getAllAttachments();
}
