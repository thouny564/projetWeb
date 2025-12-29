package com.spring.henallux.firstSpringProject.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Category {

    private Integer id;

    @NotNull(message = "French name cannot be null")
    @Size(min = 2, max = 50, message = "French name must be between 2 and 50 characters")
    private String nameFr;

    @NotNull(message = "English name cannot be null")
    @Size(min = 2, max = 50, message = "English name must be between 2 and 50 characters")
    private String nameEn;

    @Size(max = 1000, message = "French description is too long")
    private String descriptionFr;

    @Size(max = 1000, message = "English description is too long")
    private String descriptionEn;

    public Category() {
    }

    public Category(String nameFr, String nameEn, String descriptionFr, String descriptionEn) {
        this.nameFr = nameFr;
        this.nameEn = nameEn;
        this.descriptionFr = descriptionFr;
        this.descriptionEn = descriptionEn;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameFr() {
        return nameFr;
    }

    public void setNameFr(String nameFr) {
        this.nameFr = nameFr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getDescriptionFr() {
        return descriptionFr;
    }

    public void setDescriptionFr(String descriptionFr) {
        this.descriptionFr = descriptionFr;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", nameFr='" + nameFr + '\'' +
                ", nameEn='" + nameEn + '\'' +
                '}';
    }
}
