package com.catalogue.userorderservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrdersRepository extends CrudRepository<UserOrders, Long> {
    UserOrders findOrdersByUsername(String username);
}
