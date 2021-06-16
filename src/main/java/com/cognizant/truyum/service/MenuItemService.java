package com.cognizant.truyum.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.repository.MenuItemRepository;

@Service
public class MenuItemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemService.class);

	@Autowired
	MenuItemRepository menuItemRepository;

	@Transactional
	public List<MenuItem> getMenuItemListAdmin() {
		
		LOGGER.info("Start");
		List<MenuItem> list = menuItemRepository.findAll();
		LOGGER.info("End");
		return list;
	}

	@Transactional
	public List<MenuItem> getMenuItemListCustomer() {
		
		LOGGER.info("Start");
		List<MenuItem> list = menuItemRepository.findByActiveAndDateOfLaunchBefore("Yes", new Date());
		LOGGER.info("End");
		return list;
	}

	
	@Transactional
	public void modifyMenuItem(MenuItem menuItem) {
		
		LOGGER.info("Start");
		MenuItem menuItemNew = menuItemRepository.getOne(menuItem.getId());
		menuItemNew.setActive(menuItem.getActive());
		menuItemNew.setCategory(menuItem.getCategory());
		menuItemNew.setDateOfLaunch(menuItem.getDateOfLaunch());
		menuItemNew.setFreeDelivery(menuItem.getFreeDelivery());
		menuItemNew.setName(menuItem.getName());
		menuItemNew.setPrice(menuItem.getPrice());

		menuItemRepository.save(menuItemNew);
		LOGGER.info("End");
	}

	@Transactional
	public MenuItem getMenuItem(Integer menuItemId) {
		
		LOGGER.info("Start");
		Optional<MenuItem> menuItem = menuItemRepository.findById(menuItemId);
		MenuItem m = menuItem.get();
		LOGGER.info("End");
		return m;
	}

}
