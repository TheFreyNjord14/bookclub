package com.bookclub.service.impl;

import java.util.List;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;

public class MemWishlistDao implements WishlistDao {

    private List<WishlistItem> wishlist;

    public MemWishlistDao() {
        this.wishlist = List.of(
            new WishlistItem("978-0134685991", "Effective Java"),
            new WishlistItem("978-0596009205", "Head First Design Patterns")
        );
    }

    @Override
    public List<WishlistItem> list() {
        return this.wishlist;
    }

    @Override
    public WishlistItem find(String key) {
        for (WishlistItem item : this.wishlist) {
            if (item.getIsbn().equals(key)) {
                return item;
            }
        }
        return new WishlistItem();
    }
}