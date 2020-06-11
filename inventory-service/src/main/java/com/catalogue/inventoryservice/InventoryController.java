package com.catalogue.inventoryservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    public void init(){
        Book book1 = new Book("name1","isbn1");
        Book book2 = new Book("name2","isbn2");
        Book book3 = new Book("name3","isbn3");
        Book book4 = new Book("name4","isbn4");
        Book book5 = new Book("name5","isbn5");
        Book book6 = new Book("name6","isbn6");
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);
        bookRepository.save(book6);
    }

    private BookRepository bookRepository;
    @Autowired
    public void setBookRepository(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    @RequestMapping("/all")
    public List<Book> getInventory(){
        init();
        List<Book> products = new ArrayList<>();
        bookRepository.findAll().forEach(products::add);
        return products;
    }
    @RequestMapping("/search/{isbn}")
    public Book search(@PathVariable("isbn")String isbn)throws ResponseStatusException {
        init();
        if ( bookRepository.existsById(isbn) )
        return bookRepository.findById(isbn).orElse(new Book("null","null"));
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Book not found");
        }
    }
}
