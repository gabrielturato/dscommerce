package com.gturato.dscommerce.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gturato.dscommerce.enums.PaymentMethod;

import java.time.Instant;


@JsonDeserialize
public class PaymentListenerDTO {
    private Instant moment;
    private PaymentMethod method;
    private Long orderId;

    public PaymentListenerDTO(Instant moment, PaymentMethod method, Long orderId) {
        this.moment = moment;
        this.method = method;
        this.orderId = orderId;
    }

    public PaymentListenerDTO() {
    }

    public Instant getMoment() {
        return moment;
    }

    public Long getOrderId() {
        return orderId;
    }

    public PaymentMethod getMethod() {
        return method;
    }
}
