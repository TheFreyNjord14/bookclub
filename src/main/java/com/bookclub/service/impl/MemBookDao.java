package com.bookclub.service.impl;

import java.util.List;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;

public class MemBookDao implements BookDao {
    private List<Book> books;

    public MemBookDao() {
        this.books = List.of(
            new Book("978-0134685991", "Effective Java", "A comprehensive guide to best practices in Java programming.", 416, List.of("Joshua Bloch")),
            new Book("978-0596009205", "Head First Design Patterns", "A brain-friendly guide to design patterns in software development.", 694, List.of("Eric Freeman", "Elisabeth Robson")),
            new Book("978-1617294945", "Spring in Action", "A comprehensive guide to the Spring Framework for building Java applications.", 520, List.of("Craig Walls")),
            new Book("978-1491950357", "Learning Python", "A comprehensive introduction to the Python programming language.", 1648, List.of("Mark Lutz")),
            new Book("978-0132350884", "Clean Code", "A handbook of agile software craftsmanship, focusing on writing clean and maintainable code.", 464, List.of("Robert C. Martin"))
        );
    }

    @Override
    public List<Book> list() {
        return this.books;
    }

    @Override
    public Book find(String key) {
        for (Book book : this.books) {
            if (book.getIsbn().equals(key)) {
                return book;
            }
        }
        return new Book();
    }

}
