package com.ezace.springkeycloak.repository;

import com.ezace.springkeycloak.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}