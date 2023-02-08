package com.example.springsecurity2023.repository;

import com.example.springsecurity2023.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
