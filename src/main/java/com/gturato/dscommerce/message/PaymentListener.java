package com.gturato.dscommerce.message;

import com.gturato.dscommerce.dto.PaymentListenerDTO;
import com.gturato.dscommerce.services.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

    @Autowired
    PaymentService service;

    private final Logger LOG = LoggerFactory.getLogger(PaymentListener.class);

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(PaymentListenerDTO payment) {
        LOG.info("Consuming a payment: {}", payment);
        service.insertPayment(payment);
    }
}
