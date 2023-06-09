package ru.mom.remembers.attachment.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mom.remembers.attachment.model.Attachment;
import ru.mom.remembers.attachment.repository.AttachmentRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentPersistServiceImpl implements AttachmentPersistService {

    private final AttachmentRepository attachmentRepository;

    @Override
    @Transactional
    public Attachment store(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    @Override
    public Optional<Attachment> getAttachmentByName(String fileName) {
        return attachmentRepository.findAttachmentByName(fileName);
    }

    @Override
    public Optional<Attachment> getAttach(Long id) {
        return attachmentRepository.findAttachmentByNoteId(id);
    }

}
