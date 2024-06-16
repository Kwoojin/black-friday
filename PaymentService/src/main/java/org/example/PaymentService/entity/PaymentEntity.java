package org.example.PaymentService.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.example.PaymentService.enums.PaymentMethodType;
import org.example.PaymentService.enums.PaymentStatus;

import java.util.Objects;

@SuppressWarnings("JpaDataSourceORMInspection")
@Getter
@Table(
    name = "payment",
    indexes = {@Index(name = "idx_payment_user_id", columnList = "user_id")}
)
@Entity
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "order_id", unique = true)
    private Long orderId;

    @Column(name = "amount_krw")
    private Long amountKRW;

//    @Column(name = "payment_method_type")
//    private PaymentMethodType paymentMethodType;
//
//    @Column(name = "payment_data")
//    private String paymentData; // <- credit card 번호

    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "reference_code", unique = true)
    private Long referenceCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private PaymentMethodEntity paymentMethod;

    @Builder
    public PaymentEntity(Long userId, Long orderId, Long amountKRW, PaymentStatus paymentStatus, Long referenceCode, PaymentMethodEntity paymentMethod) {
        this.userId = userId;
        this.orderId = orderId;
        this.amountKRW = amountKRW;
        this.paymentStatus = paymentStatus;
        this.referenceCode = referenceCode;
        this.paymentMethod = paymentMethod;
    }

    protected PaymentEntity() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentEntity that)) {
            return false;
        }
        return this.getPaymentId() != null && this.getPaymentId().equals(that.getPaymentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPaymentId());
    }

}
