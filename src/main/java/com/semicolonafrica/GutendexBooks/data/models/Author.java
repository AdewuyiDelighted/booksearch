package com.semicolonafrica.GutendexBooks.data.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jmx.export.annotation.ManagedNotification;

import java.util.List;

@Entity
@Data
public class Author {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String birth_year;
    private String death_year;


}
