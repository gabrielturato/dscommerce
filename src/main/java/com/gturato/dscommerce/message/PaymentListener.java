package com.gturato.dscommerce.message;

import com.gturato.dscommerce.dto.PaymentListenerDTO;
import com.gturato.dscommerce.services.PaymentService;
import com.gturato.dscommerce.services.exceptions.AlreadyHasPaymentException;
import com.gturato.dscommerce.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

    @Autowired
    PaymentService service;

    private final Logger LOG = LoggerFactory.getLogger(PaymentListener.class);

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(PaymentListenerDTO payment, Acknowledgment ack) {
        LOG.info("Consuming a payment: {}", payment);
        try{
            service.insertPayment(payment);
            ack.acknowledge();
        }catch(ResourceNotFoundException | AlreadyHasPaymentException ex){
            LOG.error("Payment can't be concluded");
            ack.acknowledge();
        }catch(Exception ex){
            LOG.error("Payment error, check the logs");
        }

    }
}
