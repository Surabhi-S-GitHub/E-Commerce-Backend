package ecommerce.project.ecommerce.com.surabhi.model;

import ecommerce.project.ecommerce.com.surabhi.user.domain.PaymentMethod;
import ecommerce.project.ecommerce.com.surabhi.user.domain.PaymentStatus;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Embeddable  
public class PaymentDetails {

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String paymentId;
    private String razorpayPaymentLinkId;
    private String razorpayPaymentLinkReferenceId;
    private String razorpayPaymentLinkStatus;
    private String razorpayPaymentId;  
}
