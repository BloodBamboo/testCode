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
		 //　这个就是我们要代理的真实对象
	    private Object target;  
//	    构造方法，给我们要代理的真实对象赋初值
	    public MyInvocationHandler(Object target) {  
	        super();  
	        this.target = target;  
	    }  
	  
	    public Object getProxy() {  
	    	/**
	    	 * Thread.currentThread().getContextClassLoader(:　　一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载
	    	 * arget.getClass().getInterfaces():　　一个Interface对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，如果我提供了一组接口给它，那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了
	    	 * this:　　一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上
	    	 */
	        return Proxy.newProxyInstance(Thread.currentThread()  
	                .getContextClassLoader(), target.getClass().getInterfaces(),  
	                this);  
	    }  
	  
	    /**
	     * proxy:　　我们所代理的那个真实对象
	     * method:　　我们所要调用真实对象的某个方法的Method对象
	     * args:　　调用真实对象某个方法时接受的参数
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
		 //    我们要代理的真实对象
		UserService userService = new UserServiceImpl();  
		 //    我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);  
  
        UserService proxy = (UserService) invocationHandler.getProxy();  
        proxy.add();
        ((UserService2)proxy).add2();
	}

}
