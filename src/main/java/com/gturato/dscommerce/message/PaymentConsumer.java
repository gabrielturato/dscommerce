package com.gturato.dscommerce.message;

import com.gturato.dscommerce.dto.PaymentConsumerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {

    private final Logger LOG = LoggerFactory.getLogger(PaymentConsumer.class);

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(PaymentConsumerDTO payment) {
        LOG.info("Consuming a payment: {}", payment);
    }
}
