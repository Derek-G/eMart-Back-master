package com.kedacom.category.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 分类名字
     */
    @NotNull @Size(min = 2, max = 20) private String name;

    /**
     * 价格
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 详情
     */
    @Column(name = "detail")
    private String detail;

    public Category() {
    }

    public Category(String name, BigDecimal price, String detail) {
        this.name = name;
        this.price = price;
        this.detail = detail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
