package com.gturato.dscommerce.repositories;

import com.gturato.dscommerce.entities.OrderItem;
import com.gturato.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
