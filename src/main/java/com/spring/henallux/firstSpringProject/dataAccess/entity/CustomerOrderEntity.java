package com.spring.henallux.firstSpringProject.dataAccess.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_order")
public class CustomerOrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "paid", nullable = false)
    private Boolean paid;

    @Column(name = "status", nullable = false, length = 30)
    private String status;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private Double totalPrice;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    public CustomerOrderEntity() {}

    public CustomerOrderEntity(LocalDateTime orderDate, Boolean paid, String status, Double totalPrice, UserEntity user) {
        this.orderDate = orderDate;
        this.paid = paid;
        this.status = status;
        this.totalPrice = totalPrice;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CustomerOrderEntity{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", paid=" + paid +
                ", status='" + status + '\'' +
                ", totalPrice=" + totalPrice +
                ", user=" + user +
                '}';
    }
}
