package pos.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AOPTest {

	interface Dog {
		void info();

		void run();
	}

	static class GunDog implements Dog {

		@Override
		public void info() {
			System.out.println("I am a gundog!");
		}

		@Override
		public void run() {
			System.out.println("I run fast!");
		}

	}

	static class Authority {
		// 第一个拦截方法
		public void first() {
			System.out.println("------第一个切面方法------");
		}

		// 第二个拦截方法
		public void last() {
			System.out.println("------最后一个切面方法------");
		}
	}

	static class MyInvocationHandler implements InvocationHandler {
		// 需要被代理的对象
		private Object target;

		public void setTarget(Object target) {
			this.target = target;
		}

		// 执行动态代理对象的所有方法时，都会被替换成执行如下的invoke方法
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Exception {
			Authority authority = new Authority();
			// 执行权限对象中的in方法
			authority.first();
			System.out.println("正在执行的方法：" + method);
			// if (args != null) {
			// System.out.print("执行该方法时传入的实参：");
			// for (Object val : args) {
			// System.out.println(val);
			// }
			// } else {
			// System.out.println("调用该方法没有实参！");
			// }
			System.out.print("执行结果：");
			Object result = method.invoke(target, args);
			// 执行权限对象中的out方法
			authority.last();
			return result;
		}
	}

	static class MyProxyFactory {
		// 为制定的target生成动态代理对象
		public static Object getProxy(Object target) throws Exception {
			// 创建一个MyInvocationHandler对象
			MyInvocationHandler handler = new MyInvocationHandler();
			// 为MyInvocationHandler设置target对象
			handler.setTarget(target);
			// 创建并返回一个动态代理
			return Proxy.newProxyInstance(target.getClass().getClassLoader(),
					target.getClass().getInterfaces(), handler);
		}
	}

	public static void main(String[] args) throws Exception {
		// 创建一个原始的GunDog对象，作为target
		Dog target = new GunDog();
		// 以制定的target来创建动态代理对象
		Dog dog = (Dog) MyProxyFactory.getProxy(target);
		dog.info();
		dog.run();
	}

}