package com.bookclub.model;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class WishlistItem {

    @Id
    private String id;

    @NotNull
    @NotEmpty(message = "ISBN is a required field")
    private String isbn;

    @NotNull
    @NotEmpty(message = "Title is a required field")
    private String title;

    public WishlistItem() {}

    public WishlistItem(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    // Getters and Setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WishlistItem{id=" + id + ", isbn=" + isbn + ", title=" + title + '}';
    }
}
