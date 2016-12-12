package dao.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import utils.JdbcUtils;
import dao.BookDao;
import domain.Book;
import domain.Category;
import domain.QueryResult;

public class BookDaoImpl implements BookDao {
	public void add(Book book){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner qr = new QueryRunner();
			String sql = "insert into book(id,name,price,author,image,description,category_id) values(?,?,?,?,?,?,?)";
			Object[] params = {book.getId(),book.getName(),book.getPrice(),book.getAuthor(),book.getImage(),book.getDescription(),book.getCategory().getId()};
			qr.update(conn, sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public Book find(String id){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from book where id=?";
			return (Book) runner.query(conn,sql, id, new  BeanHandler(Book.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//String where =  "where category_id=?"
	/*
	 * 用户带where条件过来，则该方法返回分类下面的分页数
	 * 如果没带where条件，则返回所有书的分页数据
	 * 
	 * where条件的格式：String where =  "where category_id=?"
	 * 
	 */
	private List<Book> getPageData(int startindex,int pagesize,String where,Object param){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner qr = new QueryRunner();
			if(where==null || where.trim().equals("")){
				String sql = "select * from book limit ?,?";
				List<Book> list = (List<Book>) qr.query(conn, sql, new Object[]{startindex,pagesize}, new BeanListHandler(Book.class));
				return list;
			}else{
			//得到用户的分类信息
				String sql = "select * from book " + where +" limit ?,?";
				Object[] params={param,startindex,pagesize};
				List<Book> list = (List<Book>)qr.query(conn, sql, params, new BeanListHandler(Book.class) );
				return list;
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	private Integer getPageTotalRecord(String where,Object param){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner qr = new QueryRunner();
			if(where==null || where.trim().equals("")){
				String sql = "select count(*) from book";
				return ((Long)qr.query(conn, sql, new ScalarHandler())).intValue();
			}
				String sql = "select count(*) from book " + where;
				return ((Long) qr.query(conn, sql, param, new ScalarHandler())).intValue();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public QueryResult pageQuery(int startindex,int pagesize,String where,Object param){
		List<Book> list = getPageData(startindex,pagesize,where,param);
		int totalrecord = getPageTotalRecord(where,param);
		QueryResult qs = new QueryResult();
		qs.setList(list);
		qs.setTotalrecord(totalrecord);
		return qs;
	}
	
	public List<Book> getAll(){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from book";
			List list = (List) qr.query(conn, sql, new BeanListHandler(Book.class));
			return list;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
}
