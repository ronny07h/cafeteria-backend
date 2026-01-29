package com.cafeteria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @jakarta.validation.constraints.NotBlank(message = "El nombre es obligatorio")
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false)
    @jakarta.validation.constraints.NotNull(message = "El precio es obligatorio")
    @jakarta.validation.constraints.Positive(message = "El precio debe ser mayor a 0")
    private Double price;
    
    @Column(columnDefinition = "TEXT")
    @org.hibernate.validator.constraints.URL(message = "La URL de la imagen no es válida")
    private String imageUrl;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnoreProperties("products")
    private Category category;
    
    @Transient
    private Long categoryId;
    
    @PostLoad
    private void setCategoryId() {
        if (category != null) {
            this.categoryId = category.getId();
        }
    }

    public Long getCategoryId() {
        if (category != null) {
            return category.getId();
        }
        return categoryId;
    }
    
    public Product(String name, String description, Double price, String imageUrl, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.category = category;
    }
}
