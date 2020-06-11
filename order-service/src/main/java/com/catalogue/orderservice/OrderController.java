package com.catalogue.orderservice;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        List<CatalogueOrder> order = new ArrayList<>();
        orderRepository.findAll().forEach(order::add);
        return order;
    }
    @RequestMapping("/orders/{id}")
    public CatalogueOrder getOrder(@PathVariable("id") String id)throws ResponseStatusException{
        if ( orderRepository.existsById(Long.valueOf(id)) )
            return orderRepository.findById(Long.valueOf(id)).orElse(new CatalogueOrder());
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Order not found");
        }
    }
    @RequestMapping("/orders/update/{id}")
    public CatalogueOrder update(@PathVariable("id") String id, @RequestBody CatalogueOrder order)throws ResponseStatusException{
        if ( orderRepository.existsById(Long.valueOf(id)) ) {
            order.setId(Long.valueOf(id));
            orderRepository.save(order);
            return order;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Order not found");
        }
    }
    @RequestMapping("orders/create")
    public CatalogueOrder create(@RequestBody CatalogueOrder order){
        order.setId(null);
        orderRepository.save(order);
        return order;
    }
    @RequestMapping("orders/delete/{id}")
    public void delete(@PathVariable("id") String id)throws ResponseStatusException{
        if ( orderRepository.existsById(Long.valueOf(id)) ) {
            orderRepository.delete(orderRepository.findById(Long.valueOf(id)).orElse(null));
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Order not found");
        }
    }
}
