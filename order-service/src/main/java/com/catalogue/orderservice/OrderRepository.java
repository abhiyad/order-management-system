package com.catalogue.orderservice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<CatalogueOrder, Long> {
}
