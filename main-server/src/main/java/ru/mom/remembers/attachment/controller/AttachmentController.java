package ru.mom.remembers.attachment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mom.remembers.attachment.message.ResponseAttach;
import ru.mom.remembers.attachment.message.ResponseMessage;
import ru.mom.remembers.attachment.model.Attachment;
import ru.mom.remembers.attachment.service.AttachmentStorageService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/notes")
public class AttachmentController {

   private final AttachmentStorageService attachmentStorageService;

    @PostMapping(path = "/upload", headers = "Content-Type= multipart/form-data")
    public ResponseEntity<ResponseMessage> upload(@RequestParam("attachment") MultipartFile file) {
        String message = "";
        try {
            var attachment = attachmentStorageService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename() + " " + attachment.getId();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/attachment")
    public ResponseEntity<List<ResponseAttach>> getAttachments() {
        List<ResponseAttach> attachments = attachmentStorageService.getAllAttachments().map(attachment -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/notes/attachment/")
                    .path(attachment.getId().toString())
                    .toUriString();

            return new ResponseAttach(
                    attachment.getName(),
                    fileDownloadUri,
                    attachment.getType(),
                    attachment.getId(),
                    attachment.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(attachments);
    }

    @GetMapping("/attachment/{id}")
    public ResponseEntity<byte[]> getAttachment(@PathVariable Long id) {
        Attachment attach = attachmentStorageService.getAttachment(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                        attach.getName() + "\"")
                .body(attach.getData());
    }

}
