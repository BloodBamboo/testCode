import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class DynamicProxyMixin {

	interface TimeStamped {
		long getStamped();
	}
	
	static class TimeStampedImpl implements TimeStamped {
		private final long date;
		
		public TimeStampedImpl() {
			date = new Date().getTime();
		}
		
		public long getStamped() {
			return date;
		}
	}
	
	interface SerialNumbered {
		long getSerialNumbered();
	}
	
	static class SerialNumberedImpl implements SerialNumbered {
		private static long counter = 1;
		private final long serialNumber = counter++;
		public long getSerialNumbered() {
			return serialNumber;
		}
	}
	
	interface Basic {
		void set(String val);
		String get();
	}
	
	static class BasicImpl implements Basic {
		private String value;
		public void set(String val) {
			value = val;
		}
		public String get() {
			// TODO Auto-generated method stub
			return value;
		}
	}
	
	static class MixinProxy implements InvocationHandler {
		Map<String, Object> delegateByMethodMap;
		
		private MixinProxy(TwoTuple<Object, Class<?>> ...pairs) {
			delegateByMethodMap = new HashMap<String, Object>();
			for (TwoTuple<Object, Class<?>> pair : pairs) {
				for (Method method : pair.second.getMethods()) {
					String methodName = method.getName();
					if (!delegateByMethodMap.containsKey(methodName)) {
						delegateByMethodMap.put(methodName, pair.first);
					}
				}
			}
		}
		
		public Object invoke(Object proxy, Method method, Object[] arg2)
				throws Throwable {
			String methodName = method.getName();
			return method.invoke(delegateByMethodMap.get(methodName), arg2);
		}
		
		public static Object newInstance(TwoTuple<Object, Class<?>> ...pairs){
			Class[] interfaces = new Class[pairs.length];
			
			for (int i = 0, count = interfaces.length; i < count; i++) {
				interfaces[i] = (Class) pairs[i].second;
			}
			ClassLoader loader = pairs[0].first.getClass().getClassLoader();
			return Proxy.newProxyInstance(loader, interfaces, new MixinProxy(pairs));
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Object proxyObject = MixinProxy.newInstance(new TwoTuple(new BasicImpl(), Basic.class),
				new TwoTuple(new TimeStampedImpl(), TimeStamped.class),
				new TwoTuple(new SerialNumberedImpl(), SerialNumbered.class));

		Basic b = (Basic) proxyObject;
		SerialNumbered serialNumbered = (SerialNumbered) proxyObject;
		TimeStamped timeStamped = (TimeStamped) proxyObject;
		b.set("hello");
		System.out.println(b.get());
		System.out.println(serialNumbered.getSerialNumbered());
		System.out.println(timeStamped.getStamped());
		
		
		
	}
}
