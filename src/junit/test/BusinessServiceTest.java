package junit.test;

import org.junit.Test;

import domain.Book;
import domain.Cart;
import domain.Category;
import domain.PageBean;
import domain.QueryInfo;
import domain.User;
import service.impl.BusinessServiceImpl;
import utils.JdbcUtils;

public class BusinessServiceTest {

    private BusinessServiceImpl service = new BusinessServiceImpl();
	
	@Test
	public void testAddCategory(){
		Category c = new Category();
		c.setId("4");
		c.setName("web开发");
		c.setDescription("sdsdfsdf");
		service.addCategory(c);
		
		JdbcUtils.commitTransaction();
	}
	
	@Test
	public void testFindCategory(){
		service.findCategory("1");
	}
	
	@Test
	public void testGetAllCategory(){
		service.getAllCategory();
	}
	
	@Test
	public void testAddBook(){
		Category c = new Category();
		c.setId("1");
		c.setName("java开发");
		c.setDescription("sdsdfsdf");
		
		Book book = new Book();
		book.setAuthor("老张");
		book.setCategory(c);
		book.setDescription("sdfsfd");
		book.setId("34323");
		book.setImage("323");
		book.setName("javaweb开发");
		book.setPrice(89);
		
		service.addBook(book);
		JdbcUtils.commitTransaction();
	}
	
	@Test
	public void testFindBook(){
		service.findBook("34323");
	}
	
	@Test
	public void testPageQuery(){
		
		QueryInfo info = new QueryInfo();
		info.setCurrentpage(1);
		info.setPagesize(3);
		//info.setQueryname("category_id");
		//info.setQueryvalue("1");
		
		PageBean bean = service.bookPageQuery(info);
		System.out.println(bean);
	}
	
	@Test
	public void testAddUser(){
		User user = new User();
		user.setAddress("aaaa");
		user.setCellphone("2323");
		user.setEmail("cc@sina.com");
		user.setId("3");
		user.setPassword("123");
		user.setPhone("2323");
		user.setUsername("ccc");
		
		service.addUser(user);
		JdbcUtils.commitTransaction();
		
	}
	@Test
	public void testSaveOrder(){
		
		User user = new User();
		user.setAddress("aaaa");
		user.setCellphone("2323");
		user.setEmail("aa@sina.com");
		user.setId("1"); //其实只需要user的id
		user.setPassword("123");
		user.setPhone("2323");
		user.setUsername("aaa");
		
		
		Cart cart = new Cart();
		Book book = new Book();
		book.setAuthor("老张");
		book.setCategory(null);
		book.setDescription("sdfsfd");
		book.setId("34323");
		book.setImage("323");
		book.setName("javaweb开发");
		book.setPrice(89);
		cart.add(book);
		
		service.saveOrder(cart, user);
		JdbcUtils.commitTransaction();
	}
}
