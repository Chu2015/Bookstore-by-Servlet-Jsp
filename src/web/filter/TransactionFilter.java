package web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.JdbcUtils;

public class TransactionFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		try{
			//拦截下来后：获取连接、开启事务、并把连接绑定到当前线程
			chain.doFilter(request, response);
			JdbcUtils.commitTransaction();
		}finally{
			//获取当前线程上绑定的连接，提交事务，并关闭链接，释放连接与当前线程的绑定
			JdbcUtils.closeConn();
		}

	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
