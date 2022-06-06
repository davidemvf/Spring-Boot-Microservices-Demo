package com.daviDev.authModule.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "USER",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "USERNAME"),
                @UniqueConstraint(columnNames = "EMAIL")
        })
public class UserEntity {

    private Long id;

    private String username;

    private String email;

    private String password;

    private Set<RoleEntity> roles = new HashSet<>();

    public UserEntity() {
    }

    public UserEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank
    @Size(max = 20)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank
    @Size(max = 50)
    @Email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank
    @Size(max = 120)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    public Set<RoleEntity> getRoles() {
        return roles;
    }
    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
