package com.catalogue.userorderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@RestController
@RequestMapping("/orders")
public class UserOrderController {
    private UserOrdersRepository userOrdersRepository;
    @Autowired
    public void setUserOrdersRepository(UserOrdersRepository userOrdersRepository){
        this.userOrdersRepository = userOrdersRepository;
    }
    @RequestMapping("/{username}")
    public UserOrders getAllOrderID(@PathVariable("username")String  username)throws ResponseStatusException {
        if (userOrdersRepository.existsByUsername(username)) {
            return userOrdersRepository.findOrdersByUsername(username);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        }
    }
    @RequestMapping("/add/{username}/{orderId}")
    public UserOrders addOrdertoUser(@PathVariable("username")String username, @PathVariable("orderId")String orderId){
        long id = Long.valueOf(orderId);
        if (userOrdersRepository.existsByUsername(username)){
            UserOrders userOrders = userOrdersRepository.findOrdersByUsername(username);
            userOrders.addOrderID(id);
            userOrdersRepository.save(userOrders);
        }
        else{
            userOrdersRepository.save(new UserOrders(username,Arrays.asList(id)));
        }
        return userOrdersRepository.findOrdersByUsername(username);
    }
}
