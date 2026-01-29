package com.cafeteria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @jakarta.validation.constraints.NotBlank(message = "El título es obligatorio")
    private String title;

    @Column(columnDefinition = "TEXT")
    @jakarta.validation.constraints.NotBlank(message = "El contenido es obligatorio")
    private String content;

    @org.hibernate.validator.constraints.URL(message = "La URL de la imagen no es válida")
    private String imageUrl;

    private LocalDateTime publicationDate;

    @PrePersist
    protected void onCreate() {
        if (publicationDate == null) {
            publicationDate = LocalDateTime.now();
        }
    }
}
