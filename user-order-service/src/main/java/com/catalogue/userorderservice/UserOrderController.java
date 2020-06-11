package com.catalogue.userorderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public UserOrders getAllOrderID(@PathVariable("username")String  username){
        return userOrdersRepository.findOrdersByUsername(username);
    }
    @RequestMapping("/add/{username}/{orderId}")
    public UserOrders addOrdertoUser(@PathVariable("username")String username, @PathVariable("orderId")String orderId){
        long id = Long.valueOf(orderId);
        UserOrders userOrders = userOrdersRepository.findOrdersByUsername(username);
        if (userOrders!=null){
            userOrders.addOrderID(id);
            userOrdersRepository.save(userOrders);
        }
        else{
            userOrdersRepository.save(new UserOrders(username,Arrays.asList(id)));
        }
        return userOrdersRepository.findOrdersByUsername(username);
    }
}
