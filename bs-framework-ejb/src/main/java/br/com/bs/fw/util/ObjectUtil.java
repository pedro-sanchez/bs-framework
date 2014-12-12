package br.com.bs.fw.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

/**
 * @author pedro
 *
 */
public class ObjectUtil {

	public static Boolean isNull(Object object) {
		return object == null;
	}
	
	public static StringBuilder toCamelCase(String value, Boolean firstUpper) {
		StringBuilder builder = new StringBuilder();
		if(firstUpper){
			builder.append(value.substring(0, 1).toUpperCase());
		}
		else{
			builder.append(value.substring(0, 1).toLowerCase());
		}
		builder.append(value.substring(1));
		return builder;
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
	

	public static String toMD5(String value) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return convertByteToHex(MessageDigest.getInstance("MD5").digest(value.getBytes("UTF-8")));
	}
	
	private static String convertByteToHex(byte[] byteData) {

	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < byteData.length; i++) {
	        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	    }

	    return sb.toString();
	}
	
}
