package br.com.bs.fw.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import br.com.bs.fw.entity.iface.IEntity;

public class ReflectionUtil {

	public static Class<?> getTClass(Object object){
		return getTClass(object.getClass());
	}

	@SuppressWarnings("unchecked")
	public static Class<? extends IEntity> getTClass(Class<?> clazz){
		return (Class<? extends IEntity>) getGenericClass(clazz, 0);
	}

	public static Class<?> getGenericClass(Class<?> clazz, Integer genericIndex){
		try{
			Type[] types = ((ParameterizedType)clazz.getGenericSuperclass()).getActualTypeArguments();			
			return (Class<?>) types[genericIndex];
		}
		catch(Exception e){
			return null;
		}
	}
	

	public static Object getTNewInstance(Class<?> clazz){
		try {
			return getNewInstance(getTClass(clazz));		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	

	public static Object getTNewInstance(Object object){
		try {
			return getNewInstance(getTClass(object));		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	
	public static Object getNewInstance(Class<?> clazz){
		try {
			return clazz.newInstance();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public static Field[] getAllFields(Object object){
		return getAllFields(object.getClass());
	}
	
	public static Field[] getAllFields(Class<?> clazz){
		Field[] fields = clazz.getDeclaredFields();		 
		return fields;
	}
	
	public static Map<String, Method> getAllMethods(Object bean) {
          Map<String, Method> methods = new HashMap<String, Method>();
          for (Class<?> atual = bean.getClass(); !atual.equals(Object.class); atual = atual.getSuperclass()) {
                  for (Method m : atual.getMethods()) {
                          methods.put(m.getName(), m);
                  }
          }
          return methods;
	}


	public static Field getField(Object object, String fieldName) throws SecurityException, NoSuchFieldException{
		return getField(object.getClass(), fieldName);
	}
	
	public static Field getField(Class<?> clazz, String fieldName) throws SecurityException, NoSuchFieldException{
		return  clazz.getDeclaredField(fieldName);
	}	

	public static Object getFieldValue(Object object, String fieldName) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		Field field = getField(object.getClass(), fieldName);
		
		Boolean accessible = field.isAccessible();
		
		field.setAccessible(true);		
		Object result = field.get(object);		
		field.setAccessible(accessible);		
	
		return result;
	}

    public static Integer getIdByReflection(Object bean){  
        try{  
            return (Integer) getFieldValue(bean, "id");  
        }catch(Exception ex){  
            throw new RuntimeException("Não foi possível obter a propriedade'id' do item",ex);  
        }  
    }  

	public static String buildGetMethod(String campo) {
		StringBuilder builder = new StringBuilder();
		builder.append("get");
		builder.append(ObjectUtil.toCamelCase(campo, Boolean.TRUE));
		return builder.toString();
	}
	
	public static String buildSetMethod(String campo) {
		StringBuilder builder = new StringBuilder();
		builder.append("set");
		builder.append(ObjectUtil.toCamelCase(campo, Boolean.TRUE));
		return builder.toString();
	}
	
	public static Object executeMethod(String methodName, Object object, Object... parameters) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = object.getClass().getMethod(methodName);
		return method.invoke(object, parameters);
	}
	
	

}

