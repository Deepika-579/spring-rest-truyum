package com.cognizant.truyum;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.repository.MenuItemRepository;

@SpringBootTest
class TruyumSpringApplicationTests {
	
	@Autowired
	MenuItemRepository repo;

	@Test
	void contextLoads() {
	}
	
	//Test Case for Inserting the Date into MenuItem
	
	@Test
	public void createItems()
	{
		
		MenuItem m=new MenuItem();
		m.setName("Noodles");
		m.setPrice(117.00f);
		m.setActive("yes");
		m.setDateOfLaunch(Date.valueOf("2014-07-02"));
		m.setCategory("Starter");
		m.setFreeDelivery("Yes");
		repo.save(m);
		
		
	}

}
