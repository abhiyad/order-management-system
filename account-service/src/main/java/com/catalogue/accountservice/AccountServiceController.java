package com.catalogue.accountservice;


import com.catalogue.accountservice.domain.Book;
import com.catalogue.accountservice.domain.CatalogueOrder;
import com.catalogue.accountservice.domain.MyUser;
import com.catalogue.accountservice.domain.UserOrders;
import com.catalogue.accountservice.services.JwtUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.*;


@RestController
public class AccountServiceController {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private JwtUserDetailsService userService;
    @Autowired
    public void setUserDetailsService(JwtUserDetailsService userService){
        this.userService = userService;
    }
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String firstPage(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername();
        System.out.println(" =================" + username);
        return "Hello World";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody MyUser user) throws Exception {
        return ResponseEntity.ok(userService.save(user));
    }
    @GetMapping("/search/{isbn}")
    public Book search(@PathVariable("isbn")String isbn){
        Book book = restTemplate.getForObject("http://InventoryService/inventory/search/" + isbn , Book.class);
        return book;
    }
    @GetMapping("/orders")
    public List<CatalogueOrder> getAllOrders(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername();
        UserOrders response = restTemplate.getForObject("http://UserOrderService/orders/" + username, UserOrders.class);
        List<Long> orderID = response.getOrderId();
        List<CatalogueOrder>allOrders = new ArrayList<>();
        for (Long order : orderID ) {
            CatalogueOrder thisOrder = restTemplate.getForObject("http://OrderService/orders/" + order, CatalogueOrder.class);
            allOrders.add(thisOrder);
        }
        return allOrders;
    }
    @PostMapping(value="/create/{isbn}")
    public CatalogueOrder createOrder(@PathVariable("isbn")String isbn) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        Map<String, Object> map = new HashMap<>();
        map.put("username",username);
        map.put("isbn", isbn);
        map.put("sent","false");
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<CatalogueOrder> response = restTemplate.postForEntity(
                "http://OrderService/orders/create", entity, CatalogueOrder.class);
        UserOrders userOrders = restTemplate.getForObject(
                "http://UserOrderService/orders/add/" + username + "/" + String.valueOf(response.getBody().getId()),UserOrders.class);
        System.out.println(userOrders.toString());
        return response.getBody();
    }
//    @GetMapping("/search")
//    public String search(){
//        return "SEARCH";
//    }
}