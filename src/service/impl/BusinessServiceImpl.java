package service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import dao.BookDao;
import dao.CategoryDao;
import dao.OrderDao;
import dao.UserDao;
import domain.Book;
import domain.Cart;
import domain.CartItem;
import domain.Category;
import domain.Order;
import domain.OrderItem;
import domain.PageBean;
import domain.QueryInfo;
import domain.QueryResult;
import domain.User;
import factory.DaoFactory;

public class BusinessServiceImpl {
	private BookDao bdao = DaoFactory.getDao(BookDao.class);
	private UserDao udao = DaoFactory.getDao(UserDao.class);
	private OrderDao odao = DaoFactory.getDao(OrderDao.class);
	private CategoryDao cdao = DaoFactory.getDao(CategoryDao.class);

	/****************************************
	 * 
	 * 分类相关的服务
	 * 
	 **************************************/
	public void addCategory(Category c){
		cdao.add(c);
	}

	public Category findCategory(String id){
		return cdao.find(id);
	}

	public List<Category> getAllCategory(){
		return cdao.getAll();
	}

	/****************************************
	 * 
	 * 图书相关的服务
	 * 
	 **************************************/
	public void addBook(Book book){
		bdao.add(book);
	}

	public Book findBook(String id){
		return bdao.find(id);
	}

	public PageBean bookPageQuery(QueryInfo info){
		QueryResult qs =  bdao.pageQuery(info.getStartindex(),info.getPagesize(),info.getWhere(),info.getQueryvalue());
		PageBean pb = new PageBean();
		pb.setTotalrecord(qs.getTotalrecord());
		pb.setCurrentpage(info.getCurrentpage());
		pb.setPagesize(info.getPagesize());
		pb.setList(qs.getList());
		return pb;
	}
	
	public List<Book> getAllBook(){
		return bdao.getAll();
	}

	/****************************************
	 * 
	 * 用户相关的服务
	 * 
	 **************************************/
	public void addUser(User user){
		udao.add(user);
	}

	public User findUser(String username, String password){
		return udao.find(username, password);
	}

	public User findUser(String id){
		return udao.find(id);
	}

	/****************************************
	 * 
	 * 订单相关的服务
	 * 
	 ********************************/
	public void saveOrder(Cart cart, User user){
		//用用户的购物车产生订单对象，保存数据
		Order order = new Order();
		Map<String,CartItem> map = cart.getMap();
		//定义集合保存所有的购物项
		Set<OrderItem> orderitems = new HashSet();
		for(Map.Entry<String, CartItem> entry : map.entrySet()){
			OrderItem orderitem = new OrderItem();
			CartItem cartitem = entry.getValue();
			orderitem.setBook(cartitem.getBook());
			orderitem.setId(UUID.randomUUID().toString());
			orderitem.setPrice(cartitem.getPrice());
			orderitem.setQuantity(cartitem.getQuantity());
			orderitems.add(orderitem);
		}
	
		//用购物车中购物项生成订单项
		order.setId(UUID.randomUUID().toString());
		order.setOrdertime(new Date());
		order.setPrice(cart.getPrice());
		order.setState(false);
		order.setUser(user);
		order.setOrderitems(orderitems);
		
		odao.add(order);
	};

	public Order findOrder(String id){
		return odao.find(id);
	}

	public List getOrderByState(boolean state){
		return odao.getAll(state);
	}
	
	public void updateOrder(String id,boolean state){
		odao.update(id, state);
	}
	
	public List<Order> findOrderbyUser(String id){
		return odao.findbyUser(id);
	}
	
}
