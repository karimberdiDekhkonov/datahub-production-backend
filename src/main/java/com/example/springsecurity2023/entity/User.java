package com.example.springsecurity2023.entity;

import com.example.springsecurity2023.entity.enums.NewRole;
import com.example.springsecurity2023.entity.template.UUIDEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)

public class User extends UUIDEntity implements UserDetails {

    @Column(nullable = false)
    private String firstname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String initialLetter;

    @ManyToOne
    private Company joinedCompanyId;

    private String salary;

    private int securityCode;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = false;

    @Enumerated(EnumType.STRING)
    private NewRole role;

    public User(String firstname, String email, String password, NewRole role, Integer securityCode) {
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.securityCode = securityCode;
    }


    @OneToOne(fetch=FetchType.LAZY)
    private Attachment avatarId;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @PrePersist
    @PreUpdate
    public void setLetter(){
        this.initialLetter = firstname.substring(0,1).toUpperCase();
    }
}
