package com.sudeshh.ecommerce.service;

import com.sudeshh.ecommerce.dto.OrderRequest;
import com.sudeshh.ecommerce.model.*;
import com.sudeshh.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;

    public Order placeOrder(OrderRequest request, String username) {
        User user = userService.findByUsername(username);

        List<OrderItem> items = request.getItems().stream().map(itemRequest -> {
            Product product = productService.getProductById(itemRequest.getProductId());

            if (product.getStock() < itemRequest.getQuantity()) {
                throw new RuntimeException("Insufficient stock for: " + product.getName());
            }

            product.setStock(product.getStock() - itemRequest.getQuantity());

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setPrice(product.getPrice() * itemRequest.getQuantity());
            return item;
        }).collect(Collectors.toList());

        Double totalPrice = items.stream()
                .mapToDouble(OrderItem::getPrice).sum();

        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(items);
        order.setTotalPrice(totalPrice);
        order.setStatus(Order.OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        items.forEach(item -> item.setOrder(order));

        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(String username) {
        User user = userService.findByUsername(username);
        return orderRepository.findByUserId(user.getId());
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrderStatus(Long orderId, Order.OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found with id: " + orderId));
        order.setStatus(status);
        return orderRepository.save(order);
    }
}