package ru.mom.remembers.attachment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.mom.remembers.attachment.jpa.AttachmentPersistService;
import ru.mom.remembers.attachment.model.Attachment;
import ru.mom.remembers.exception.BadRequestException;
import ru.mom.remembers.exception.NotFoundException;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentPersistService attachmentPersistService;

    public Attachment store(MultipartFile file, Long noteId) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        var fileDB = attachmentPersistService.getAttachmentByName(fileName);

        if (fileDB.isEmpty() || !fileName.equals(fileDB.get().getName())) {
            Attachment attachment = new Attachment(fileName, file.getContentType(), noteId, file.getBytes());
            return attachmentPersistService.store(attachment);
        }
        throw new BadRequestException("Bad request body", "Attachment with this name already exists");
    }

    @Override
    public Attachment getAttach(Long noteId) {

        Attachment attach = attachmentPersistService.getAttach(noteId).orElseThrow(() ->
                new NotFoundException("The required object was not found.",
                        String.format("Attach with id = %s was not found.", noteId)));

        return attach;

    }
}
