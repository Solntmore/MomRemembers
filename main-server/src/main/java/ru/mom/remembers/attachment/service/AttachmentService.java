package ru.mom.remembers.attachment.service;

import org.springframework.web.multipart.MultipartFile;
import ru.mom.remembers.attachment.message.ResponseAttach;
import ru.mom.remembers.attachment.model.Attachment;

import java.io.IOException;

public interface AttachmentService {

    ResponseAttach store(MultipartFile file, Long noteId) throws IOException;

    Attachment getAttach(Long noteId);
}
