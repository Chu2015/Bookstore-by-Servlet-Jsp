package factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import domain.Privilege;
import domain.User;
import service.BusinessService;
import service.impl.BusinessServiceImpl;
import utils.Permission;

public class ServiceFactory {
	private static ServiceFactory factory = new ServiceFactory();
	private ServiceFactory(){	}
	public static ServiceFactory getInstance(){
		return  factory;
	}
	
	BusinessServiceImpl service = new BusinessServiceImpl();
	
	public BusinessService getServiceProxy(final User user){
		//�õ�web����õķ���
		return (BusinessService)Proxy.newProxyInstance(ServiceFactory.class.getClassLoader(), service.getClass().getInterfaces(), new InvocationHandler(){

			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				//�������ʵ��������Ӧ�ķ����������ʵ���󷽷�����û��Ȩ��ע��
				String name = method.getName();
				Method realmethod = service.getClass().getMethod(name, method.getParameterTypes());
				Permission permission = realmethod.getAnnotation(Permission.class);
				if(permission==null){
					realmethod.invoke(service, args);
				}
				//��ʵ������Ӧ�ķ�������Ȩ��ע�⣬��õ����ʸ÷�����Ȩ��
				String value = permission.value();
				Privilege p = new Privilege(value);
				if(user==null){
					throw new SecurityException("��δ��¼");
				}
				
				List<Privilege> list = service.getUserAllPrivilege(user);
				if(list.contains(p)){
					return method.invoke(service, args);
				}else{
					throw new SecurityException("��û��Ȩ��");
				}
			}
			
		});
	}
	
	
	
	
	
	
	
}
