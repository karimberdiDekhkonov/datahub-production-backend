package com.example.springsecurity2023.entity;

import com.example.springsecurity2023.entity.template.UUIDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)

public class MainTable extends UUIDEntity {

    //AVOIDING FROM ANNOTATIONS (@Data, @NoArgsConstructor, @AllArgsConstructor).

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch=FetchType.EAGER)
    private Company companyId;

    public MainTable() {
    }

    public MainTable(String name, Company companyId) {
        this.name = name;
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }
}
