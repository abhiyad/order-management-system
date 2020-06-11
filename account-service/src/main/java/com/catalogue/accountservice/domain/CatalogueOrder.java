package com.catalogue.accountservice.domain;
import javax.persistence.*;

@Entity
@Table(name="CatalogueOrders")
public class CatalogueOrder {
    private String username;
    private String isbn;
    private Boolean sent;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String getUsername() {
        return username;
    }

    public void setUsernaname(String username) {
        this.username = username;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CatalogueOrder(String username, String isbn, Boolean sent) {
        this.username = username;
        this.isbn = isbn;
        this.sent = sent;
    }

    public CatalogueOrder(){}
}
