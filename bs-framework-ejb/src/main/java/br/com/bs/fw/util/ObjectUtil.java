package br.com.bs.fw.util;

import java.util.Collection;

/**
 * @author pedro
 *
 */
public class ObjectUtil {

	public static Boolean isNull(Object object) {
		return object == null;
	}

	/**
	 * @param object
	 * @return
	 */
	public static Boolean isEmpty(Object object) {
		if (isNull(object)) {
			return Boolean.TRUE;
		}

		if (object instanceof String) {
			return ((String) object).isEmpty();
		}

		if (object instanceof Collection<?>) {
			return ((Collection<?>) object).isEmpty();
		}

		return Boolean.FALSE;
	}
	/**
	 * @param objects
	 * @return any elments is empty
	 */
	public static Boolean hasEmpty(Object... objects) {
		for (Object object : objects) {
			if(isEmpty(object)){
				return Boolean.TRUE;
			}
		}

		return Boolean.FALSE;
	}
	
	
	/**
	 * @param objects
	 * @return all elments is empty
	 */
	public static Boolean isEmpty(Object... objects) {
		for (Object object : objects) {
			if(!isEmpty(object)){
				return Boolean.FALSE;
			}
		}
		
		return Boolean.TRUE;
	}
	
	public static Boolean isEqual(Object object1, Object object2) {
		if (isEmpty(object1) || isEmpty(object2)) {
			return isEmpty(object1) && isEmpty(object2);
		}
		return object1.equals(object2);
	}
}
