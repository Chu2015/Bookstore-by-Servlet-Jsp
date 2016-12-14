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
		//得到web层调用的方法
		return (BusinessService)Proxy.newProxyInstance(ServiceFactory.class.getClassLoader(), service.getClass().getInterfaces(), new InvocationHandler(){

			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				//反射出真实对象上相应的方法，检查真实对象方法上有没有权限注解
				String name = method.getName();
				Method realmethod = service.getClass().getMethod(name, method.getParameterTypes());
				Permission permission = realmethod.getAnnotation(Permission.class);
				if(permission==null){
					realmethod.invoke(service, args);
				}
				//真实对象相应的方法上有权限注解，则得到访问该方法的权限
				String value = permission.value();
				Privilege p = new Privilege(value);
				if(user==null){
					throw new SecurityException("还未登录");
				}
				
				List<Privilege> list = service.getUserAllPrivilege(user);
				if(list.contains(p)){
					return method.invoke(service, args);
				}else{
					throw new SecurityException("你没有权限");
				}
			}
			
		});
	}
	
	
	
	
	
	
	
}
