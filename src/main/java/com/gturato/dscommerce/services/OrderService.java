package com.gturato.dscommerce.services;

import com.gturato.dscommerce.dto.OrderDTO;
import com.gturato.dscommerce.dto.OrderItemDTO;
import com.gturato.dscommerce.entities.*;
import com.gturato.dscommerce.repositories.OrderItemRepository;
import com.gturato.dscommerce.repositories.OrderRepository;
import com.gturato.dscommerce.repositories.ProductRepository;
import com.gturato.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    OrderRepository repository;

    @Autowired
    UserService userService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso nao encontrado"));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto){
        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();
        order.setClient(user);

        for(OrderItemDTO itemDTO: dto.getItems()){
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            System.out.println("Order" + order);
            System.out.println("Product" + product);
            System.out.println("Quantity" + itemDTO.getQuantity());
            System.out.println("Price" + product.getPrice());
            OrderItem orderItem = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
            order.getItems().add(orderItem);
        }

        repository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);
    }
}
