package web.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.impl.BusinessServiceImpl;
import domain.Dbback;

public class DbServlet extends HttpServlet {
	private BusinessServiceImpl service = new BusinessServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("backup")){
			backup (request, response);
			return;
		}
		if(method.equals("list")){
			list (request, response);
			return;
		}
		if(method.equals("restore")){
			
			restore (request, response);
			return;
		}
	}
	private void restore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try{
			String id = request.getParameter("id");
			String backpath = this.getServletContext().getRealPath("/backup");
			Dbback back = service.findDbback(id);
			String filename = back.getFilename();
			String command="cmd /c mysql -uroot -proot mybookstore<"+backpath+"\\"+filename;
			Runtime.getRuntime().exec(command);
			request.setAttribute("message", "�ָ��ɹ�");
		}catch(Exception e){
			request.setAttribute("message", "�ָ�ʧ��");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		
	}
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list = service.getAllDbback();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/manager/listdbback.jsp").forward(request, response);
	}
	private void backup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
			String backpath = this.getServletContext().getRealPath("/backup");
			String filename = System.currentTimeMillis()+".sql";
			
			//�����ݲ����������ݿⱸ�ݵ�Ӳ��
			String command = "cmd /c mysqldump -uroot -proot mybookstore>"+backpath+"\\"+filename;
			Runtime.getRuntime().exec(command);
			//�����ݿ�洢���ݶ���
			Dbback back = new Dbback();
			back.setBacktime(new Date());
			back.setDescription(request.getParameter("description"));
			back.setFilename(filename);
			back.setId(UUID.randomUUID().toString());
			request.setAttribute("message", "���ݳɹ�");
			service.addDbback(back);
		}catch(Exception e){
			request.setAttribute("message", "����ʧ��");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request,response);
	
	}

}
