package br.com.bs.fw.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

public class JSFUtils {
	
	public static SelectItem getSelectItem(Object object, Map<String, String> fieldMap) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		if (ObjectUtil.isEmpty(object) || ObjectUtil.isEmpty(fieldMap)){
			return null;
		}
		
		Object id = null;
		if (fieldMap.containsKey("id")) {
			String methodName = ReflectionUtil.buildGetMethod(fieldMap.get("id"));
			id = ReflectionUtil.executeMethod(methodName, object);
		}
		
		String name = null;
		if (fieldMap.containsKey("name")) {
			String methodName = ReflectionUtil.buildGetMethod(fieldMap.get("name"));
			name = (String) ReflectionUtil.executeMethod(methodName, object);
		}
		
		return new SelectItem(id,name);
	}
	
	public static List<SelectItem> getSelectItens(List<?> objectList, Map<String, String> fieldMap){
		List<SelectItem> result =  new ArrayList<SelectItem>();
		if (ObjectUtil.isEmpty(objectList) || ObjectUtil.isEmpty(fieldMap)){
			return result;
		}
						
		try {
			for (Object object : objectList) {
				result.add(getSelectItem(object, fieldMap));
			}
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

}
