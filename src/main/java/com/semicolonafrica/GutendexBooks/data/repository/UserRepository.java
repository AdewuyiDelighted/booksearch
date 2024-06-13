package com.semicolonafrica.GutendexBooks.data.repository;

import com.semicolonafrica.GutendexBooks.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

}
