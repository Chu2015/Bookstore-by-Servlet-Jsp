package dao.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import dao.CategoryDao;
import domain.Category;

public class CategoryDaoImpl implements CategoryDao {

	public void add(Category c){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner qr = new QueryRunner();
			String sql = "insert into category(id,name,description) values(?,?,?)";
			Object[] params = {c.getId(),c.getName(),c.getDescription()};
			qr.update(conn, sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public Category find(String id){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from category where id=?";
			Category  c = (Category) qr.query(conn,sql, id, new BeanHandler(Category.class));
			return c;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public List getAll(){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from category";
			List<Category>  list = (List<Category>) qr.query(conn, sql, new BeanListHandler(Category.class));
			return list;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
