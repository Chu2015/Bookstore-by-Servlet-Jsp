package dao.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import utils.JdbcUtils;
import dao.UserDao;
import domain.Order;
import domain.User;

public class UserDaoImpl implements UserDao {

	public void add(User user){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner qr = new QueryRunner();
			String sql = "insert into user(id,username,password,phone,cellphone,email,address) values(?,?,?,?,?,?,?)";
			Object[] params = {user.getId(),user.getUsername(),user.getPassword(),user.getPhone(),user.getCellphone(),user.getEmail(),user.getAddress()};
			qr.update(conn, sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public User find(String id){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from user where id=?";
			User user = (User) qr.query(conn, sql, id, new BeanHandler(User.class));
			return user;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	
	public User find(String username,String password){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from user where username=? and password=?";
			Object[] params={username,password};
			User user = (User) qr.query(conn, sql, params, new BeanHandler(User.class));
			return user;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
