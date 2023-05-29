package ru.mom.remembers.attachment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.mom.remembers.attachment.model.Attachment;
import ru.mom.remembers.attachment.repository.AttachmentRepository;

import java.io.IOException;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AttachmentStorageServiceImpl implements AttachmentStorageService {

    private final AttachmentRepository attachmentRepository;

    public Attachment store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Attachment attachment = new Attachment(fileName, file.getContentType(), file.getBytes());

        return attachmentRepository.save(attachment);
    }

    public Attachment getAttachment(Long id) {
        return attachmentRepository.findById(id).get();
    }

    public Stream<Attachment> getAllAttachments() {
        return attachmentRepository.findAll().stream();
    }
}
