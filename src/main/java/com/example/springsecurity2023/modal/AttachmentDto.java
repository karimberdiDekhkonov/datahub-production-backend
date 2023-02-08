package com.example.springsecurity2023.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AttachmentDto {

    private String fileName;
    private String downloadURL;
    private String fileType;
    private long fileSize;
}
