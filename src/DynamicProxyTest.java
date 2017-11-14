import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class DynamicProxyTest {

	interface UserService {  
	    public abstract void add();  
	}  
	
	interface UserService2 {  
	    public abstract void add2();  
	}  
	
	
	static class UserServiceImpl implements UserService, UserService2 {  
		  
	    public void add() {  
	        System.out.println("----- add -----");  
	    }

		public void add2() {
			System.out.println("----- add 2-----");  
		}  
	} 
	
	
	static class MyInvocationHandler implements InvocationHandler {  
		 //�������������Ҫ�������ʵ����
	    private Object target;  
//	    ���췽����������Ҫ�������ʵ���󸳳�ֵ
	    public MyInvocationHandler(Object target) {  
	        super();  
	        this.target = target;  
	    }  
	  
	    public Object getProxy() {  
	    	/**
	    	 * Thread.currentThread().getContextClassLoader(:����һ��ClassLoader���󣬶��������ĸ�ClassLoader�����������ɵĴ��������м���
	    	 * arget.getClass().getInterfaces():����һ��Interface��������飬��ʾ�����ҽ�Ҫ������Ҫ����Ķ����ṩһ��ʲô�ӿڣ�������ṩ��һ��ӿڸ�������ô���������������ʵ���˸ýӿ�(��̬)�������Ҿ��ܵ�������ӿ��еķ�����
	    	 * this:����һ��InvocationHandler���󣬱�ʾ���ǵ��������̬��������ڵ��÷�����ʱ�򣬻��������һ��InvocationHandler������
	    	 */
	        return Proxy.newProxyInstance(Thread.currentThread()  
	                .getContextClassLoader(), target.getClass().getInterfaces(),  
	                this);  
	    }  
	  
	    /**
	     * proxy:����������������Ǹ���ʵ����
	     * method:����������Ҫ������ʵ�����ĳ��������Method����
	     * args:����������ʵ����ĳ������ʱ���ܵĲ���
	     */
	    public Object invoke(Object proxy, Method method, Object[] args)  
	            throws Throwable {  
	        System.out.println("----- before -----");  
	        Object result = method.invoke(target, args);  
	        System.out.println("----- after -----");  
	        return result;  
	    }
	}  
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 //    ����Ҫ�������ʵ����
		UserService userService = new UserServiceImpl();  
		 //    ����Ҫ�����ĸ���ʵ���󣬾ͽ��ö��󴫽�ȥ�������ͨ������ʵ�����������䷽����
        MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);  
  
        UserService proxy = (UserService) invocationHandler.getProxy();  
        proxy.add();
        ((UserService2)proxy).add2();
	}

}
