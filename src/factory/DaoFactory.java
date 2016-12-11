package factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {
	
	private static Properties p = new Properties();
	
	private DaoFactory(){
		try {
			InputStream in = DaoFactory.class.getClassLoader().getResourceAsStream("factory/dao.properties");
			p.load(in);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static final DaoFactory daofactory = new DaoFactory();
	public DaoFactory getInstance(){
		return this.daofactory;
	}

	public static <T> T getDao (Class<T> interfacename){
			try {
				String dao = interfacename.getSimpleName();
				String daoname = p.getProperty(dao);
				return (T) Class.forName(daoname).newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	}
}
