package com.gturato.dscommerce.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PaymentMethod {
    DEBIT_CARD("DC"),CREDIT_CARD("CC"),CASH("CA"),TICKET("TI");

    private String code;

    PaymentMethod(String code) {
        this.code = code;
    }
}
