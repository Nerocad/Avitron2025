package com.nerocad.spring.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_type", nullable = false)
    private String productType;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "supplier_price", nullable = false)
    private int supplierPrice;

    @Column(name = "sale_price")
    private int salePrice;

    @Column(name = "sale_date")
    private LocalDate saleDate;

    @Column(name = "is_sold", nullable = false)
    private boolean isSold;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Sale() {
    }

    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(int supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        if (id == null || sale.id == null) return false;
        return supplierPrice == sale.supplierPrice &&
                salePrice == sale.salePrice &&
                isSold == sale.isSold &&
                Objects.equals(id, sale.id) &&
                Objects.equals(productType, sale.productType) &&
                Objects.equals(productName, sale.productName) &&
                Objects.equals(saleDate, sale.saleDate) &&
                Objects.equals(createdAt, sale.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", productType='" + productType + '\'' +
                ", productName='" + productName + '\'' +
                ", supplierPrice=" + supplierPrice +
                ", salePrice=" + salePrice +
                ", saleDate=" + saleDate +
                ", isSold=" + isSold +
                ", createdAt=" + createdAt +
                '}';
    }
}
