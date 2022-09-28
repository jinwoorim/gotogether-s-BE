package com.gotogether.gotogethersbe.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_ID")
    private Long id;
    private String name;
    private String value;
    private Long additional;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public Option addProduct(Product product){
        this.product = product;
        return this;
    }
}
