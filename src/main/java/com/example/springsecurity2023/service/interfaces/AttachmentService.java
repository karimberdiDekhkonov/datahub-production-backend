package com.example.springsecurity2023.service.interfaces;

import com.example.springsecurity2023.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface AttachmentService {
    Attachment uploadFile(MultipartFile file);

    Attachment getAttachment(UUID fileId) throws Exception;
}
