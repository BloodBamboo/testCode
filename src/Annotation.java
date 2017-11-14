import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.management.RuntimeErrorException;
import javax.swing.DefaultBoundedRangeModel;

public class Annotation {

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Constrains {
		boolean a() default false;

		boolean b() default false;

		boolean c() default false;
	}

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface SQLInteger {
		String name() default "";

		Constrains constrains() default @Constrains;

		int value() default 0;
	}

	@SQLInteger(name = "jason", constrains = @Constrains(a = true))
	public int name;
	@SQLInteger(10)
	public int id;

	public static class ExThread implements Runnable {
		public void run() {
			throw new RuntimeErrorException(null);
		}
	}

	public static class myUnEX implements Thread.UncaughtExceptionHandler {

		public void uncaughtException(Thread arg0, Throwable arg1) {
			arg1.printStackTrace();
			System.out.println("myUnEX  : " + arg0.getId());
		}
	}

	public static class ThreadF implements ThreadFactory {
		public Thread newThread(Runnable r) {
			Thread thread = new Thread(r);
			thread.setUncaughtExceptionHandler(new myUnEX());
			return thread;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// ExecutorService executorService =
			// Executors.newCachedThreadPool();
			ExecutorService executorService = Executors.newCachedThreadPool(new ThreadF());
			executorService.execute(new ExThread());
		} catch (Exception e) {
			System.out.println("ExThread has been Handed");
		}
	}

}
