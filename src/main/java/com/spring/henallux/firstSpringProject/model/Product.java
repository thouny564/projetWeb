package com.spring.henallux.firstSpringProject.model;

import javax.validation.constraints.*;

public class Product {

    private Integer id;

    @NotNull(message = "French name cannot be null")
    @Size(min = 2, max = 100, message = "French name must be between 2 and 100 characters")
    private String nameFr;

    @NotNull(message = "English name cannot be null")
    @Size(min = 2, max = 100, message = "English name must be between 2 and 100 characters")
    private String nameEn;

    @NotNull(message = "French description cannot be null")
    @Size(min = 1, max = 2000, message = "French description is too long")
    private String descriptionFr;

    @NotNull(message = "English description cannot be null")
    @Size(min = 1, max = 2000, message = "English description is too long")
    private String descriptionEn;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Stock cannot be null")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;

    @Size(max = 255, message = "Image URL is too long")
    private String imageUrl;

    @NotNull(message = "Active cannot be null")
    private Boolean active = true;

    @NotNull(message = "Category cannot be null")
    private Category category;

    public Product() {}

    public Product(String nameFr, String nameEn, String descriptionFr, String descriptionEn,
                   Double price, Integer stock, String imageUrl, Boolean active, Category category) {
        this.nameFr = nameFr;
        this.nameEn = nameEn;
        this.descriptionFr = descriptionFr;
        this.descriptionEn = descriptionEn;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.active = active;
        this.category = category;
    }


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNameFr() { return nameFr; }
    public void setNameFr(String nameFr) { this.nameFr = nameFr; }

    public String getNameEn() { return nameEn; }
    public void setNameEn(String nameEn) { this.nameEn = nameEn; }

    public String getDescriptionFr() { return descriptionFr; }
    public void setDescriptionFr(String descriptionFr) { this.descriptionFr = descriptionFr; }

    public String getDescriptionEn() { return descriptionEn; }
    public void setDescriptionEn(String descriptionEn) { this.descriptionEn = descriptionEn; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    @Override
    public String toString() {
        return "Product{" +
                "nameFr='" + nameFr + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", active=" + active +
                '}';
    }
}
