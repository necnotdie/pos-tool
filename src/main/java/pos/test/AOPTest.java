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
		// ��һ�����ط���
		public void first() {
			System.out.println("------��һ�����淽��------");
		}

		// �ڶ������ط���
		public void last() {
			System.out.println("------���һ�����淽��------");
		}
	}

	static class MyInvocationHandler implements InvocationHandler {
		// ��Ҫ������Ķ���
		private Object target;

		public void setTarget(Object target) {
			this.target = target;
		}

		// ִ�ж�̬�����������з���ʱ�����ᱻ�滻��ִ�����µ�invoke����
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Exception {
			Authority authority = new Authority();
			// ִ��Ȩ�޶����е�in����
			authority.first();
			System.out.println("����ִ�еķ�����" + method);
			// if (args != null) {
			// System.out.print("ִ�и÷���ʱ�����ʵ�Σ�");
			// for (Object val : args) {
			// System.out.println(val);
			// }
			// } else {
			// System.out.println("���ø÷���û��ʵ�Σ�");
			// }
			System.out.print("ִ�н����");
			Object result = method.invoke(target, args);
			// ִ��Ȩ�޶����е�out����
			authority.last();
			return result;
		}
	}

	static class MyProxyFactory {
		// Ϊ�ƶ���target���ɶ�̬�������
		public static Object getProxy(Object target) throws Exception {
			// ����һ��MyInvocationHandler����
			MyInvocationHandler handler = new MyInvocationHandler();
			// ΪMyInvocationHandler����target����
			handler.setTarget(target);
			// ����������һ����̬����
			return Proxy.newProxyInstance(target.getClass().getClassLoader(),
					target.getClass().getInterfaces(), handler);
		}
	}

	public static void main(String[] args) throws Exception {
		// ����һ��ԭʼ��GunDog������Ϊtarget
		Dog target = new GunDog();
		// ���ƶ���target��������̬�������
		Dog dog = (Dog) MyProxyFactory.getProxy(target);
		dog.info();
		dog.run();
	}

}