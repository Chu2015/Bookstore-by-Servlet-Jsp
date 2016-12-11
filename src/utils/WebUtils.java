package utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import service.impl.BusinessServiceImpl;
import domain.Book;
import domain.Category;

public class WebUtils {

	public static <T> T request2bean(HttpServletRequest request,Class<T> beanclass){
		
		try {
			Map map = request.getParameterMap();
			T bean = beanclass.newInstance();
			BeanUtils.populate(bean, map);
			return bean;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	
	}
	
	public static Book upload(HttpServletRequest request,String savepath){
		try{
			Book book = new Book();
			book.setId(UUID.randomUUID().toString());
			DiskFileItemFactory factory= new DiskFileItemFactory();
			ServletFileUpload fileupload = new ServletFileUpload(factory);
			fileupload.setHeaderEncoding("UTF-8");//设置字符
			List<FileItem> list = fileupload.parseRequest(request);
			for(FileItem item : list){
				if(item.isFormField()){
					String name=item.getFieldName();
					String value=item.getString("UTF-8");//解决乱码问题
					if(name.equals("category_id")){
						BusinessServiceImpl service = new BusinessServiceImpl();
						Category category = service.findCategory(value);
						book.setCategory(category);
					}else{
						BeanUtils.copyProperty(book, name, value);//封装除了image外的其他属性
					}
				}else{
					InputStream in = item.getInputStream();
					String filename = item.getName();
					//filename = filename.substring(filename.lastIndexOf("\\")+1);
					String savefilename = UUID.randomUUID().toString()+filename;
					FileOutputStream out = new FileOutputStream(savepath+"\\"+savefilename);
					int len = 0;
					byte[] buffer = new byte[1024];
	                while((len=in.read(buffer))>0){
						out.write(buffer, 0, len);
					}
					book.setImage(savefilename);//封装iamge属性
					out.close();
					in.close();
					item.delete();
				}
			}
			return book;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
}
