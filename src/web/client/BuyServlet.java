package web.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Book;
import domain.Cart;
import service.impl.BusinessServiceImpl;

public class BuyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BusinessServiceImpl service = new BusinessServiceImpl();
		String bookid = request.getParameter("id");
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		Book book=service.findBook(bookid);
		if(cart==null){
			cart = new Cart();
			cart.add(book);
			session.setAttribute("cart", cart);
		}else{
			cart.add(book);
		}
		request.setAttribute("cart", cart);
		request.getRequestDispatcher("/client/listcart.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
