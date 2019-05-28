package com.pizza.app;

import com.pizza.app.dao.impl.PizzaDAOImpl;
import com.pizza.app.entity.Pizza;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PizzaAppApplicationTests {

	@Autowired
	private PizzaDAOImpl pizzaDAO;

	@Test
	public void get() {
		List<Pizza> users = pizzaDAO.get();
		assertNotNull(users);
		assertTrue(!users.isEmpty());
	}

	@Test
	public void findPizzaById() {
		Pizza pizza = pizzaDAO.get(1);
		System.out.println(pizza.toString());
		assertNotNull(pizza);
	}

	@Test
	public void createUser() {
		Pizza pizza = new Pizza(6, "Onion", 50,70);
		pizzaDAO.add(pizza);
		Pizza newPizza = pizzaDAO.get(pizza.getId());
		assertNotNull(newPizza);
		assertEquals("Onion", newPizza.getInfo());
		assertEquals(70, newPizza.getPrice());
	}

	@Test
	public void deletePizzaById(){
		pizzaDAO.delete(5);
	}
}
