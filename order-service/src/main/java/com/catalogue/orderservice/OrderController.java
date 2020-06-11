package com.catalogue.orderservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {
    private OrderRepository orderRepository;
    @Autowired
    public void setOrderRepository(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @RequestMapping("/orders")
    public List<CatalogueOrder> getAllOrders(){
        orderRepository.save(new CatalogueOrder("abhiyad1","1",Boolean.FALSE) );
        orderRepository.save(new CatalogueOrder("abhiyad2","2",Boolean.FALSE) );
        List<CatalogueOrder> order = new ArrayList<>();
        orderRepository.findAll().forEach(order::add);
        return order;
    }
    @RequestMapping("/orders/{id}")
    public CatalogueOrder getOrder(@PathVariable("id") String id){
        return orderRepository.findById(Long.valueOf(id)).orElse(null);
    }
    @PostMapping("/orders/update/{id}")
    public CatalogueOrder update(@PathVariable("id") String id, @RequestBody CatalogueOrder order){
        order.setId(Long.valueOf(id));
        orderRepository.save(order);
        return order;
    }
    @PostMapping("orders/create")
    public CatalogueOrder create(@RequestBody CatalogueOrder order){
        orderRepository.save(order);
        return order;
    }
    @RequestMapping("orders/delete/{id}")
    public void delete(@PathVariable("id") String id){
        orderRepository.delete(orderRepository.findById(Long.valueOf(id)).orElse(null));
    }
}
