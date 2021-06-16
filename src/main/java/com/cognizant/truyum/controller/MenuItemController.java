package com.cognizant.truyum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.MenuItemService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class MenuItemController {

	@Autowired
	private MenuItemService menuItemService;
	
	
	// Displaying the list of menu Items for Admin

	@GetMapping("/show-menu-list-admin")
	public String showtMenuItemListAdmin(ModelMap model) {
		log.info("Start");
		List<MenuItem> menuItemList = menuItemService.getMenuItemListAdmin();
		log.debug("Admin Menu List: {}", menuItemList);
		model.addAttribute("menuItemList", menuItemList);
		log.info("End");
		return "menu-item-list-admin";
	}

	// Displaying the list of menu items for Customer
	
	@GetMapping("/show-menu-list-customer")
	public String showtMenuItemListCustomer(ModelMap model) {
		log.info("Start");
		List<MenuItem> menuItemList = menuItemService.getMenuItemListCustomer();
		model.addAttribute("menuItemList", menuItemList);
		log.info("End");
		return "menu-item-list-customer";
	}
	
	@GetMapping("/show-edit-menu-item")
	public String showMenuItem(ModelMap model, Integer menuItemId) {
		log.info("Start");
		MenuItem item = menuItemService.getMenuItem(menuItemId);
		model.addAttribute("menuItem", item);
		log.debug("Items:{}", item);
		log.info("End");
		return "edit-menu-item";
	}

	// Displaying status of editing menu item
	
	@PostMapping(value = "/edit-menu-item")
	public String showModifyMenuItem(@ModelAttribute("menuItem") MenuItem menuItem, BindingResult result) {
		log.info("Start");
		if (result.hasErrors()) {
			log.debug("{}", result.getFieldErrors());
		}
		if (menuItem.getFreeDelivery() == null) {
			menuItem.setFreeDelivery("No");
		}
		menuItem.setDateOfLaunch(menuItem.getDateOfLaunch());
		menuItemService.modifyMenuItem(menuItem);
		log.info("End");
		return "edit-menu-item-status";
	}

}
