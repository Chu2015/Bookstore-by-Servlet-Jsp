package web.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Category;
import domain.PageBean;
import domain.QueryInfo;
import service.impl.BusinessServiceImpl;
import utils.WebUtils;

public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BusinessServiceImpl service = new BusinessServiceImpl();
		List<Category> list = service.getAllCategory();
		
		QueryInfo info = WebUtils.request2bean(request, QueryInfo.class);
		
//		info.setCurrentpage(Integer.valueof(request.getParameter("currentpage")));
//		info.setPagesize(Integer.valueof(request.getParameter("pagesize")));
		if(request.getParameter("category_id")!=null){
			info.setQueryname("category_id");
			info.setQueryvalue(request.getParameter("category_id"));
		}
		PageBean pb = service.bookPageQuery(info);
		
		request.setAttribute("categories", list);
		request.setAttribute("pagebean", pb);
		request.getRequestDispatcher("/client/index.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
