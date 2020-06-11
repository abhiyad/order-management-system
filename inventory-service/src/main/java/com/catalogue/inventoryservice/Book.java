package com.catalogue.inventoryservice;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {
    private String name;
    @Id
    private String isbn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Book(String name, String isbn) {
        this.name = name;
        this.isbn = isbn;
    }

    public Book(){}

}
