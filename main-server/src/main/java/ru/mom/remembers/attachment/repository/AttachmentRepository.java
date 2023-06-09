package ru.mom.remembers.attachment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mom.remembers.attachment.model.Attachment;

import java.util.Optional;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    Optional<Attachment> findAttachmentByNoteId(Long id);

    Optional<Attachment> findAttachmentByName(String fileName);
}
