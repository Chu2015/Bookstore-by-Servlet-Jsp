package utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {

	private static DataSource datasource = new ComboPooledDataSource();
	private static DataSource datasource_back = new ComboPooledDataSource("back");
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	
	public static DataSource getDataSource(){
		return datasource;
	}
	public static DataSource getDataSource_back(){
		return datasource_back;
	}
	
	public static Connection getConnection() throws SQLException{
		Connection conn = local.get();
		if(conn==null){
			conn = datasource.getConnection();
			local.set(conn);
		}
		conn.setAutoCommit(false);
		return conn;
	}
	
	public static void commitTransaction(){
	
		try {
			Connection conn = getConnection();
			if(conn==null){
				return;
			}
			conn.commit();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void closeConn(){
		Connection conn = null;
		try{
			conn = getConnection();
			if(conn!=null){
				conn.close();
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			local.remove();
		}
		
	}

}
