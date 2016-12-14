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
	 * ������صķ���
	 * 
	 **************************************/
	void addCategory(Category c);

	Category findCategory(String id);

	List<Category> getAllCategory();

	/****************************************
	 * 
	 * ͼ����صķ���
	 * 
	 **************************************/
	void addBook(Book book);

	Book findBook(String id);

	PageBean bookPageQuery(QueryInfo info);

	List<Book> getAllBook();

	/****************************************
	 * 
	 * �û���صķ���
	 * 
	 **************************************/
	void addUser(User user);

	User findUser(String username, String password);

	User findUser(String id);

	/****************************************
	 * 
	 * ������صķ���
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