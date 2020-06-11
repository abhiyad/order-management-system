package com.catalogue.userorderservice;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="UserOrders")
public class UserOrders {
    @Id
    private String username;
    @Convert(converter = StringListConverter.class)
    private List<Long> orderId;

    public UserOrders(String username, List<Long> orderId) {
        this.username = username;
        this.orderId = orderId;
    }

    public UserOrders() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Long> getOrderId() {
        return orderId;
    }

    public void setOrderId(List<Long> orderId) {
        this.orderId = orderId;
    }

    public void addOrderID(Long id){
        this.orderId.add(id);
    }
}
