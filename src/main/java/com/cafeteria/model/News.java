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
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String imageUrl;

    private LocalDateTime publicationDate;

    @PrePersist
    protected void onCreate() {
        if (publicationDate == null) {
            publicationDate = LocalDateTime.now();
        }
    }
}
