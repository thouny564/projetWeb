package com.spring.henallux.firstSpringProject.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class CustomerOrder {

    private Integer id;

    @NotNull
    private LocalDateTime orderDate;

    @NotNull
    private Boolean paid;

    @NotNull
    @Size(min = 1, max = 30, message = "Status must be between 1 and 30 characters")
    private String status;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true, message = "Total price must be >= 0")
    private double totalPrice;

    @NotNull
    private User user;

    public CustomerOrder() {}

    public CustomerOrder(LocalDateTime orderDate, Boolean paid, String status, double totalPrice, User user) {
        this.orderDate = orderDate;
        this.paid = paid;
        this.status = status;
        this.totalPrice = totalPrice;
        this.user = user;
    }


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public Boolean getPaid() { return paid; }
    public void setPaid(Boolean paid) { this.paid = paid; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", paid=" + paid +
                ", status='" + status + '\'' +
                ", totalPrice=" + totalPrice +
                ", user=" + user +
                '}';
    }
}
