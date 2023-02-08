package com.example.springsecurity2023.service;

import com.example.springsecurity2023.entity.Attachment;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.repository.AttachmentRepository;
import com.example.springsecurity2023.service.interfaces.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service

public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;

    @Override
    public Attachment uploadFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")){
                throw new Exception();
            }
            Attachment attachment = new Attachment(
                    fileName,
                    file.getContentType(),
                    file.getBytes()
            );
            return  attachmentRepository.save(attachment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Attachment getAttachment(UUID fileId) throws Exception {
        return attachmentRepository.findById(fileId).orElseThrow(()->new Exception("File not found with id: "+fileId));
    }
}
