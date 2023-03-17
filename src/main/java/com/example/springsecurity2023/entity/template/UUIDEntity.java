package com.example.springsecurity2023.entity.template;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.UUID;

@MappedSuperclass
public class UUIDEntity {

    //AVOIDING FROM ANNOTATIONS (@Data, @NoArgsConstructor, @AllArgsConstructor).

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
