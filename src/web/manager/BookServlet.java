package web.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Book;
import service.impl.BusinessServiceImpl;
import utils.WebUtils;

public class BookServlet extends HttpServlet {
	BusinessServiceImpl service = new BusinessServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("forAddUI")){
			forAddUI(request,response);
		}
		if(method.equals("add")){
			add(request,response);
		}
		if(method.equals("list")){
			list(request,response);
		}

	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> list = service.getAllBook();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/manager/listbook.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String savepath=this.getServletContext().getRealPath("/image");
			System.out.println(savepath);
			Book book = WebUtils.upload(request, savepath);
			service.addBook(book);
			request.setAttribute("message", "添加书籍成功");
		}catch(Exception e){
			request.setAttribute("message", "添加书籍失败");
		}
			request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	private void forAddUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List list = service.getAllCategory();
		request.setAttribute("categories", list);
		request.getRequestDispatcher("/manager/addbook.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
