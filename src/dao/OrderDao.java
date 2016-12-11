package dao;

import java.util.List;

import domain.Order;

public interface OrderDao {

	void add(Order o);

	Order find(String id);

	/*
	 * state:true:�ѷ���
	 * state:false;δ����
	 */
	List<Order> getAll(boolean state);

	void update(String id, boolean state);
	
	List<Order> findbyUser(String userid);
}