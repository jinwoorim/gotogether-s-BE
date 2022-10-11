package com.gotogether.gotogethersbe.domain;

import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_OPTION_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private String name;

    private String value;

    private long additional;

    private boolean required;

    @Builder
    ProductOption (Long id, String name, String value, long additional, boolean required) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.additional = additional;
        this.required = required;
    }

    public ProductOption addProduct(Product product){
        this.product = product;
        return this;
    }
}
