package com.cognizant.truyum.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.repository.CartRepository;
import com.cognizant.truyum.repository.MenuItemRepository;


@Service
public class CartItemService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CartItemService.class);
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	MenuItemRepository menuItemRepository;

	@Transactional
	public void deleteCartItem(int id) {
		LOGGER.info("Start");
		cartRepository.deleteById(id);
		LOGGER.info("End");	
	}


	@Transactional
	public List<Cart> getCartItems() {
		
		LOGGER.info("Start");
		List<Cart> cartItems = cartRepository.findAll();
		LOGGER.info("End");
		return cartItems;
	}


	public void addToCart(int id) {
		
		Optional<MenuItem> menuItem = menuItemRepository.findById(id);
		Cart cart = new Cart(menuItem.get());
		cartRepository.save(cart);
	}
}
