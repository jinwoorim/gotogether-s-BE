package com.gotogether.gotogethersbe.domain;

import com.gotogether.gotogethersbe.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @CreationTimestamp
    private LocalDate reservationDate = LocalDate.now();

    private long totalPrice;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Reservation(Product product, Member member, long totalPrice, Status status) {
        this.product = product;
        this.member = member;
        this.totalPrice = totalPrice;
        this.status = status;
    }
}
