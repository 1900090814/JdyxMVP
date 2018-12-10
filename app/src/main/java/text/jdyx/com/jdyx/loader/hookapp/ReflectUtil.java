package text.jdyx.com.jdyx.loader.hookapp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 创建日期：2018/7/2 0002 on 10:53
 * 描述:
 * 作者:侯宇航 Administrator
 */

public class ReflectUtil {
	static Object getMember(Class clazz, Object target, String memberName){
		try {
			Field field = clazz.getDeclaredField(memberName);
			if(!Modifier.isPublic(field.getModifiers())){
				field.setAccessible(true);
			}
			return field.get(target);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	static void setMember(Class clazz, Object target, String memberName,Object newValue){
		try {
			Field field = clazz.getDeclaredField(memberName);
			if(!Modifier.isPublic(field.getModifiers())){
				field.setAccessible(true);
			}
			field.set(target,newValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static Object invoke(Class clazz, Object target, String methodName, Class[] parameterTypes, Object[] parameters){
		try {
			Method method = clazz.getDeclaredMethod(methodName,parameterTypes);
			if(!Modifier.isPublic(method.getModifiers())){
				method.setAccessible(true);
			}
			return method.invoke(target, parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
