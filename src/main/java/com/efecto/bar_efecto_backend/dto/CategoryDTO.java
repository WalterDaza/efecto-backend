package com.efecto.bar_efecto_backend.dto;

public class    CategoryDTO {
    private Long id;
    private String nameCategory;
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategoryDTO(Long id, String nameCategory, String image) {
        this.id = id;
        this.nameCategory = nameCategory;
        this.image = image;
    }

    public CategoryDTO() {
    }
}
