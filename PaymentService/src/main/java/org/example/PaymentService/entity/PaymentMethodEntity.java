package org.example.PaymentService.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.example.PaymentService.enums.PaymentMethodType;

import java.util.Objects;

@SuppressWarnings("JpaDataSourceORMInspection")
@Getter
@Table(
    name = "payment_method",
    indexes = {@Index(name = "idx_payment_method_user_id", columnList = "user_id")}
)
@Entity
public class PaymentMethodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_id")
    private Long paymentMethodId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "payment_method_type")
    private PaymentMethodType paymentMethodType;

    @Column(name = "credit_card_number")
    private String creditCardNumber;

    protected PaymentMethodEntity() {}

    @Builder
    public PaymentMethodEntity(Long userId, PaymentMethodType paymentMethodType, String creditCardNumber) {
        this.userId = userId;
        this.paymentMethodType = paymentMethodType;
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentMethodEntity that)) {
            return false;
        }
        return this.getPaymentMethodId() != null && this.getPaymentMethodId().equals(that.getPaymentMethodId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPaymentMethodId());
    }


}
