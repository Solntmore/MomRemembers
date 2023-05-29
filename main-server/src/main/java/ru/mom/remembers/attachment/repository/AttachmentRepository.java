package ru.mom.remembers.attachment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mom.remembers.attachment.model.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {



}
