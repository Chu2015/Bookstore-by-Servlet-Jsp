package junit.test;

import java.util.List;

import org.junit.Test;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import domain.Order;
import domain.User;

public class OrderDaoTest {

	@Test
	public void findTest(){
		
		OrderDao dao = new OrderDaoImpl();
		dao.find("1");
	}

}
