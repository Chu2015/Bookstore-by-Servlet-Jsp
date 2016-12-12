package dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import dao.DbbackDao;
import domain.Dbback;

public class DbbackDaoImpl implements DbbackDao {

	public void add(Dbback back){
		try{
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource_back());
			String sql = "insert into dbback(id,filename,backtime,description) values(?,?,?,?)";
			Object[] params={back.getId(),back.getFilename(),back.getBacktime(),back.getDescription()};
			qr.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public List<Dbback> list(){
		try{
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource_back());
			String sql = "select * from dbback";
			return (List<Dbback>) qr.query(sql,new BeanListHandler(Dbback.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public Dbback find(String id){
		try{
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource_back());
			String sql = "select * from dbback where id=?";
			return (Dbback) qr.query(sql, id, new BeanHandler(Dbback.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
