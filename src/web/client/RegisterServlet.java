package web.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.impl.BusinessServiceImpl;
import utils.WebUtils;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
		BusinessServiceImpl service = new BusinessServiceImpl();
		User user = WebUtils.request2bean(request, User.class);
		user.setId(UUID.randomUUID().toString());
		service.addUser(user);
		request.setAttribute("message", "×¢²á³É¹¦");
		}catch(Exception e){
			request.setAttribute("message", "×¢²áÊ§°Ü");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
