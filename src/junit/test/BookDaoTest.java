package junit.test;

import org.junit.Test;

import dao.BookDao;
import dao.impl.BookDaoImpl;

public class BookDaoTest {

	@Test
	public void testQuery(){
		
		BookDao dao = new BookDaoImpl();
		dao.pageQuery(0, 3, "where category_id=?", 1);
	}

}
