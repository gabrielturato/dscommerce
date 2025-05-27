package com.gturato.dscommerce.dto;

import com.gturato.dscommerce.entities.Category;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Categories of products")
public class CategoryDTO {

    Long id;
    String name;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO(Category entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
