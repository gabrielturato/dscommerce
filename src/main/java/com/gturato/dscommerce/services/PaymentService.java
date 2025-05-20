package com.gturato.dscommerce.services;

import com.gturato.dscommerce.dto.PaymentListenerDTO;
import com.gturato.dscommerce.entities.Order;
import com.gturato.dscommerce.entities.OrderStatus;
import com.gturato.dscommerce.entities.Payment;
import com.gturato.dscommerce.repositories.OrderRepository;
import com.gturato.dscommerce.repositories.PaymentRepository;
import com.gturato.dscommerce.services.exceptions.AlreadyHasPaymentException;
import com.gturato.dscommerce.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository repository;

    @Autowired
    OrderRepository orderRepository;

    private final Logger LOG = LoggerFactory.getLogger(PaymentService.class);

    public void insertPayment(PaymentListenerDTO dto){
        Order order = orderRepository.findById(dto.getOrderId()).orElseThrow(() -> {
                    LOG.error("Doesn't exist a order for this payment");
                    return new ResourceNotFoundException("Resource not found");
                });

        if(order.getPayment() != null){
            LOG.error("Order already has a payment, paymentID {}",order.getPayment().getId());
            throw new AlreadyHasPaymentException("Order has a payment already.");
        }

        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);

        Payment payment = new Payment();

        payment.setMoment(dto.getMoment());
        payment.setMethod(dto.getMethod());
        payment.setOrder(order);

        repository.save(payment);
    }
}
