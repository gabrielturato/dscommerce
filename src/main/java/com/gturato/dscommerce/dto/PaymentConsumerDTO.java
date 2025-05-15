package com.gturato.dscommerce.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gturato.dscommerce.enums.PaymentMethod;

import java.time.Instant;


@JsonDeserialize
public class PaymentConsumerDTO {
    private Instant moment;
    private PaymentMethod method;
    private Long orderId;

    public PaymentConsumerDTO(Instant moment, PaymentMethod method, Long orderId) {
        this.moment = moment;
        this.method = method;
        this.orderId = orderId;
    }

    public PaymentConsumerDTO() {
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
