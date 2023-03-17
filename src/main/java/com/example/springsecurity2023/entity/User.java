package com.example.springsecurity2023.entity;

import com.example.springsecurity2023.entity.enums.NewRole;
import com.example.springsecurity2023.entity.template.UUIDEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@EqualsAndHashCode(callSuper = true)

public class User extends UUIDEntity implements UserDetails {

    //AVOIDING FROM ANNOTATIONS (@Data, @NoArgsConstructor, @AllArgsConstructor).

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

    public User() {
    }

    public User(String firstname, String email, String password, String initialLetter, Company joinedCompanyId, String salary, int securityCode, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, NewRole role, Attachment avatarId) {
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.initialLetter = initialLetter;
        this.joinedCompanyId = joinedCompanyId;
        this.salary = salary;
        this.securityCode = securityCode;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.role = role;
        this.avatarId = avatarId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getInitialLetter() {
        return initialLetter;
    }

    public Company getJoinedCompanyId() {
        return joinedCompanyId;
    }

    public String getSalary() {
        return salary;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public NewRole getRole() {
        return role;
    }

    public Attachment getAvatarId() {
        return avatarId;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setInitialLetter(String initialLetter) {
        this.initialLetter = initialLetter;
    }

    public void setJoinedCompanyId(Company joinedCompanyId) {
        this.joinedCompanyId = joinedCompanyId;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRole(NewRole role) {
        this.role = role;
    }

    public void setAvatarId(Attachment avatarId) {
        this.avatarId = avatarId;
    }
}
