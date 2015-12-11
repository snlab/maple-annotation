package org.tongji.wx.an;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MapleAppWrapper {
	
	static class CglibProxy implements MethodInterceptor{
		Object target;

		public Object getInstance(Object target){
			this.target = target;
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(this.target.getClass());
			enhancer.setCallback(this);
			return enhancer.create();
		}
		@Override
		public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
			////System.out.print;
			Object result = null;
			if (method.isAnnotationPresent(Multitable.class)) {
				Multitable annotation = method.getAnnotation(Multitable.class);
	            //System.out.print;
	            try {
					result = proxy.invokeSuper(obj, args);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
			else {result = proxy.invokeSuper(obj, args);
			}
		    return result;
		}
		
	}
	
	public static void main(String[] args){
		MapleApp ma = new MapleApp();
		//MapleApp p = (MapleApp)Proxy.newProxyInstance(MapleApp.class.getClassLoader(), MapleApp.class.getInterfaces(), new Initalizer(ma));
		CglibProxy pro = new CglibProxy();
		MapleApp p = (MapleApp)pro.getInstance(ma);
		Packet pkt = new Packet();
		p.onPacketIn(pkt);
	}
}
