package com.example.springsecurity2023.controller;

import com.example.springsecurity2023.entity.Attachment;
import com.example.springsecurity2023.modal.AttachmentDto;
import com.example.springsecurity2023.service.interfaces.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.*;
import java.util.UUID;

@Controller
@RequestMapping("/api/attachment")

public class AttachmentController {

    //TODO

    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public AttachmentDto uploadFile(@RequestParam("file")MultipartFile file){
        String downloadURL = "";
        Attachment attachment = attachmentService.uploadFile(file);
        downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(String.valueOf(attachment.getId()))
                .toUriString();

        return new AttachmentDto(
                file.getOriginalFilename(),
                downloadURL,
                file.getContentType(),
                file.getSize()
        );
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable UUID id) throws Exception {
        Attachment attachment = attachmentService.getAttachment(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }
}
