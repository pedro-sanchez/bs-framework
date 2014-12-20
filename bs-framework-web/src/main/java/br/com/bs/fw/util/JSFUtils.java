package br.com.bs.fw.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.bs.fw.entity.iface.IEntity;

public class JSFUtils {

	public static List<SelectItem> getSelectItens(List<? extends IEntity> objectList) {
		if (ObjectUtil.isEmpty(objectList)) {
			return new ArrayList<SelectItem>();
		}

		Class<?> clazz = objectList.get(0).getClass();
		Map<String, String> fieldMap = SearchUtil.getIdName(clazz);

		if (ObjectUtil.isEmpty(fieldMap)) {
			return new ArrayList<SelectItem>();
		}
	
		String value = fieldMap.get("id");
		String field = fieldMap.get("name");
		
		return getSelectItems(value, objectList, field);

	}

	public static SelectItem getSelectItem(Object object, String... fields) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return getSelectItem("-", object, fields);
	}
	
	public static SelectItem getSelectItem(String separator, Object object, String... fields) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringBuilder label = new StringBuilder();
		if (ObjectUtil.isEmpty(separator)) {
			separator = "-";
		}

		for (String field : fields) {
			if (!ObjectUtil.isEmpty(field)) {
				if (label.length() > 0) {
					label.append(" ").append(separator.trim()).append(" ");
				}
				Object itemLabel = ReflectionUtil.executeGetMethod(field, object);

				if (!ObjectUtil.isEmpty(itemLabel)) {
					label.append(itemLabel);
				}
			}
		}
		
		return new SelectItem(object, label.toString());
	}

	public static List<SelectItem> getSelectItems(List<? extends IEntity> list, String... fields) {
		return getSelectItems("-", list, fields);
	}
	
	public static List<SelectItem> getSelectItems(String separator, List<? extends IEntity> list, String... fields) {
		List<SelectItem> result = new ArrayList<>();
		try {
			for (Object object : list) {
				result.add(getSelectItem(separator, object, fields));
			}
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {

		}
		
		return result;
	}
	
	@SafeVarargs
	public static <E extends Enum<E>> List<SelectItem> enumSelectItens(boolean sort, E... enums) {
		ArrayList<SelectItem> result = new ArrayList<SelectItem>();

		for (E enumObject : enums) {
			result.add(new SelectItem(enumObject, getEnumLabel(enumObject)));
		}

		if (sort) {
			Collections.sort(result, new Comparator<SelectItem>() {

				@Override
				public int compare(SelectItem item1, SelectItem item2) {
					return item1.getLabel().compareTo(item2.getLabel());
				}

			});
		}

		return result;
	}


	public static String getEnumLabel(Enum<?> enumItem) {
		try {
			return getMessage(enumItem.getDeclaringClass().getSimpleName() + "." + enumItem.name());
		} catch (Exception e) {
			return (enumItem.getDeclaringClass().getSimpleName() + "." + enumItem.name());
		}
	}
	
	public static String getMessage(String key){
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		String message = bundle.getString(key);
		
		return message;
	}

}
