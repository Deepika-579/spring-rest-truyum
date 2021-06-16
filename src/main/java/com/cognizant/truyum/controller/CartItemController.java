package com.cognizant.truyum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.CartItemService;
import com.cognizant.truyum.service.MenuItemService;

@Controller

public class CartItemController {

	@Autowired
	private MenuItemService menuItemService;

	@Autowired
	private CartItemService cartItemService;
	
	
	//  Adding Items To The Cart

	@GetMapping("/add-to-cart")
	public String addToCart(@RequestParam("menuItemId") int id, ModelMap model) {
		List<MenuItem> menuItemList = menuItemService.getMenuItemListCustomer();
		cartItemService.addToCart(id);
		model.addAttribute("addCartStatus", "added");
		model.addAttribute("menuItemList", menuItemList);
		return "menu-item-list-customer-notification";
	}
	
	
	// Displaying all The items Present in the Cart

	@GetMapping("/cart")
	public String showAllItemsInCart(ModelMap model) {
		String view = null;
		List<Cart> items = cartItemService.getCartItems();
		float total = 0;
		if (items.isEmpty()) {
			view = "cart-empty";
		}

		else {
			for (Cart cartItem : items) {
				total += cartItem.getItem().getPrice();
			}
			model.addAttribute("total", total);
			model.addAttribute("cart", items);
			view = "cart";
		}
		return view;

	}
	
	// Deleting the Cart Item Based on Id

	@GetMapping("/delete")
	public String deleteItem(ModelMap model, @RequestParam("productId") int id) {
		cartItemService.deleteCartItem(id);
		List<Cart> items = cartItemService.getCartItems();
		model.addAttribute("cart", items);
		float total = 0f;
		for (Cart cartItem : items) {
			total += cartItem.getItem().getPrice();
		}
		model.addAttribute("total", total);
		return "cart-notification";
	}

}
