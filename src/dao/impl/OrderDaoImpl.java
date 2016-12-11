package dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import dao.OrderDao;
import domain.Book;
import domain.Order;
import domain.OrderItem;
import domain.User;

public class OrderDaoImpl implements OrderDao {

	public void add(Order o){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner qr = new QueryRunner();
			String sql = "insert into orders(id,ordertime,state,price,user_id) values(?,?,?,?,?)";
			Object[] params = {o.getId(),o.getOrdertime(),o.isState(),o.getPrice(),o.getUser().getId()};
			qr.update(conn, sql, params);
			
			Set<OrderItem> set = o.getOrderitems();
			for(OrderItem item : set){
				sql = "insert into orderitem(id,quantity,price,book_id,order_id) values(?,?,?,?,?)";
				params = new Object[]{item.getId(),item.getQuantity(),item.getPrice(),item.getBook().getId(),o.getId()};
				qr.update(conn,sql, params);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	};
	
	public Order find(String id){
		try{
			//找出订单的信息
			Connection conn = JdbcUtils.getConnection();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from orders where id=?";
			Order order = (Order) qr.query(conn, sql, id, new BeanHandler(Order.class));
			//找出order中的每一个订单项
			sql="select * from orderitem where order_id=?";
			List<OrderItem> list = (List) qr.query(conn, sql, id, new BeanListHandler(OrderItem.class));
	
			//找出每一个订单项代表的书
			for(OrderItem oi :list){
				String oiid = oi.getId();
				sql="select b.* from orderitem oi,book b where oi.id=? and oi.book_id=b.id";
				Book book = (Book) qr.query(conn, sql, oiid, new BeanHandler(Book.class));
				oi.setBook(book);
			}
			order.getOrderitems().addAll(list);
			//找出下订单的人
			sql = "select user.* from orders,user where orders.id=? and orders.user_id=user.id";
			User user = (User) qr.query(conn, sql, id, new BeanHandler(User.class));
			order.setUser(user);
			return order;
		}catch(Exception e){
			throw new RuntimeException(e);
		}

	}
	/*
	 * state:true:已发货
	 * state:false;未发货
	 */
	public List<Order> getAll(boolean state){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from Orders where state=?";
			List<Order> list = (List<Order>) qr.query(conn, sql, state, new BeanListHandler(Order.class));
			
			for(Order o :list){
				sql = "select user.* from orders,user where orders.id=? and orders.user_id=user.id";
				User user = (User) qr.query(conn, sql, o.getId(), new BeanHandler(User.class));
				o.setUser(user);
			}
			return list;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void update(String id,boolean state){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner qr = new QueryRunner();
			String sql ="update orders set state=? where id=?";
			Object[] params = {state,id};
			qr.update(conn, sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	//更加用户的id，找到订单
	public List<Order> findbyUser(String userid){
		try{
			//找出订单的信息
			Connection conn = JdbcUtils.getConnection();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from orders where user_id=?";
			List<Order> orderlist = (List<Order>) qr.query(conn, sql, userid, new BeanListHandler(Order.class));
			//找出order中的每一个订单项
			for(Order order : orderlist){
				sql="select * from orderitem where order_id=?";
				List<OrderItem> list = (List<OrderItem>) qr.query(conn, sql,order.getId(),new BeanListHandler(OrderItem.class) );
				//找出每一个订单项代表的书
				for(OrderItem oi :list){
					String oiid = oi.getId();
					sql="select b.* from orderitem oi,book b where oi.id=? and oi.book_id=b.id";
					Book book = (Book) qr.query(conn, sql, oiid, new BeanHandler(Book.class));
					oi.setBook(book);
				}
				order.getOrderitems().addAll(list);
			}
			return orderlist;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
