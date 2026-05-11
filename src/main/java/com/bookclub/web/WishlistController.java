package com.bookclub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import com.bookclub.service.impl.MongoWishlistDao;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private WishlistDao wishlistDao = new MongoWishlistDao();

    @RequestMapping(method = RequestMethod.GET)
    public String showWishlist() {
        return "wishlist/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/new")
    public String wishlistForm(Model model) {
        model.addAttribute("wishlistItem", new WishlistItem());
        return "wishlist/new";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "wishlist/new";
        }

        wishlistItem.setUsername(authentication.getName());

        wishlistDao.add(wishlistItem);

        return "redirect:/wishlist";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showWishlistItem(@PathVariable String id, Model model) {
        WishlistItem wishlistItem = wishlistDao.find(id);
        
        model.addAttribute("wishlistItem", wishlistItem);

        return "wishlist/view";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public String updateWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult, Authentication authentication)
    {

        wishlistItem.setUsername(authentication.getName());

        if (bindingResult.hasErrors())
        {
            return "wishlist/view/";
        }

        wishlistDao.update(wishlistItem);

        return "redirect:/wishlist";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/remove/{id}")
    public String removeWishlistItem(@PathVariable String id)
    {
        wishlistDao.remove(id);

        return "redirect:/wishlist";
    }

    @Autowired
    public void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }
}