package com.ezace.springkeycloak.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @Column(name = "id", length = 150, nullable = false)
    private String id;

    @Column(name = "hash_pwd", length = 500, nullable = false)
    private String hashPwd;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "email", length = 300, nullable = false)
    private String email;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "cpf", length = 50, nullable = false)
    private String cpf;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "department")
    private String department;
}