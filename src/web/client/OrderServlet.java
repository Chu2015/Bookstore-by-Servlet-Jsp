package web.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.impl.BusinessServiceImpl;
import domain.Cart;
import domain.Order;
import domain.User;

public class OrderServlet extends HttpServlet {

	private BusinessServiceImpl service = new BusinessServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   
			String method = request.getParameter("method");
			if(method.equals("generate")){
				generate(request,response);
				return;
			}
			if(method.equals("find")){
				find(request,response);
				return;
			}
	}

	private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		List<Order> list = service.findOrderbyUser(user.getId());
		request.setAttribute("list", list);
		request.getRequestDispatcher("/client/listorder.jsp").forward(request, response);
	}


	private void generate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			request.setAttribute("message", "请先登录");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		try{
			BusinessServiceImpl service = new BusinessServiceImpl();
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			service.saveOrder(cart, user);
			request.setAttribute("message", "生成订单成功");
		}catch(Exception e){
			request.setAttribute("message", "生成订单失败");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
