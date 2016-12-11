package web.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Order;
import service.impl.BusinessServiceImpl;

public class OrderServlet extends HttpServlet {
	BusinessServiceImpl service = new BusinessServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String method = request.getParameter("method");
		if(method.equals("getAll")){
			getAll(request,response);
			return;
		}
		if(method.equals("find")){
			find(request,response);
			return;
		}
		if(method.equals("update")){
			update(request,response);
			return;
		}
	
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		String id = request.getParameter("id");
		service.updateOrder(id, true);
		request.setAttribute("message","订单状态已更新为已发货");
		}
		catch(Exception e){
			request.setAttribute("message","更新失败");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		
	}
	private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Order order = service.findOrder(id);
		request.setAttribute("order", order);
		request.getRequestDispatcher("/manager/orderdetail.jsp").forward(request, response);
	}
	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	     boolean state = Boolean.parseBoolean(request.getParameter("state"));
		 List<Order> list = service.getOrderByState(state);
		 request.setAttribute("list", list);
		 request.getRequestDispatcher("/manager/listorder.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
