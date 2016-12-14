package web.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Category;
import domain.User;
import factory.ServiceFactory;
import service.BusinessService;
import service.impl.BusinessServiceImpl;
import utils.WebUtils;

public class CategoryServlet extends HttpServlet {
	BusinessService service = new BusinessServiceImpl();
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("add")){
			add(request,response);
		}
		if(method.equals("getAll")){
			getAll(request,response);
		}
		
	}
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
			User user = (User) request.getSession().getAttribute("user");
			BusinessService service = ServiceFactory.getInstance().getServiceProxy(user);
			Category category = WebUtils.request2bean(request, Category.class);
			category.setId(UUID.randomUUID().toString());
			service.addCategory(category);
			request.setAttribute("message", "添加分类成功");
		}catch(Exception e){
			if(e instanceof SecurityException){
				request.setAttribute("message",e.getMessage());
			}else{
				e.printStackTrace();
				request.setAttribute("message", "添加分类失败");
			}
		}
			request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List list = service.getAllCategory();
		request.setAttribute("categories", list);
		request.getRequestDispatcher("/manager/listcategory.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
