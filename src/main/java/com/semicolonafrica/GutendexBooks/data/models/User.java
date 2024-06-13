package com.semicolonafrica.GutendexBooks.data.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean isLocked = true;

}
