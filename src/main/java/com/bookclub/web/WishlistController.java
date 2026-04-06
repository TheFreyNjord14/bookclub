package com.bookclub.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.impl.MemWishlistDao;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    @RequestMapping(method = RequestMethod.GET)
    public String showWishlist(Model model) {
        MemWishlistDao wishlistDao = new MemWishlistDao();
        List<WishlistItem> wishlist = wishlistDao.list();

        for (WishlistItem item : wishlist) {
            System.out.println(item.toString());
        }

        model.addAttribute("wishlist", wishlist);
        return "wishlist/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/new")
    public String wishlistForm(Model model) {
        model.addAttribute("wishlistItem", new WishlistItem());
        return "wishlist/new";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "wishlist/new";
        }

        return "redirect:/wishlist";
    }
}