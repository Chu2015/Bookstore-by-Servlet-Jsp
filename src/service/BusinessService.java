package service;

import java.util.List;

import domain.Book;
import domain.Cart;
import domain.Category;
import domain.Dbback;
import domain.Order;
import domain.PageBean;
import domain.Privilege;
import domain.QueryInfo;
import domain.User;

public interface BusinessService {

	/****************************************
	 * 
	 * 分类相关的服务
	 * 
	 **************************************/
	void addCategory(Category c);

	Category findCategory(String id);

	List<Category> getAllCategory();

	/****************************************
	 * 
	 * 图书相关的服务
	 * 
	 **************************************/
	void addBook(Book book);

	Book findBook(String id);

	PageBean bookPageQuery(QueryInfo info);

	List<Book> getAllBook();

	/****************************************
	 * 
	 * 用户相关的服务
	 * 
	 **************************************/
	void addUser(User user);

	User findUser(String username, String password);

	User findUser(String id);

	/****************************************
	 * 
	 * 订单相关的服务
	 * 
	 ********************************/
	void saveOrder(Cart cart, User user);

	Order findOrder(String id);

	List getOrderByState(boolean state);

	void updateOrder(String id, boolean state);

	List<Order> findOrderbyUser(String id);

	void addDbback(Dbback back);

	List getAllDbback();

	Dbback findDbback(String id);

	List getUserAllPrivilege(User user);
}