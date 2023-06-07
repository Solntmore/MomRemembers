package ru.mom.remembers.attachment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mom.remembers.attachment.model.Attachment;
import ru.mom.remembers.attachment.service.AttachmentService;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/attachment")
@Tag(name = "Attachment-controller", description = "Управляет вложениями к записи пользователей")
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping(path = "/{noteId}", headers = "Content-Type= multipart/form-data")
    @Operation(summary = "Загружает вложение",
            description = "Позволяет загрузить изображение к определенной записи и сохранить его в БД")
    public ResponseEntity<Attachment> upload(@RequestParam("attachment") MultipartFile file,
                                             @PathVariable("noteId") Long noteId) throws IOException {

        return ResponseEntity.status(HttpStatus.OK).body(attachmentService.store(file, noteId));
    }


    @GetMapping("/{noteId}")
    @Operation(summary = "Получает вложения",
            description = "Позволяет получить изображение к определенной записи")
    public ResponseEntity<byte[]> getAttachment(@PathVariable Long noteId) {

        Attachment attach = attachmentService.getAttach(noteId);

        return ResponseEntity.ok()
             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attach.getName() + "\"")
             .body(attach.getData());
    }

}
