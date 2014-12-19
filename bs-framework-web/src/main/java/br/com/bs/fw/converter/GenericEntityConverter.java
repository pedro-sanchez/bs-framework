package br.com.bs.fw.converter;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter("genericEntityConverter")
public class GenericEntityConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		if (value != null) {
			return this.getAttributesFrom(component).get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {

		if (value == null) {
			return null;
		}
		if (value instanceof String) {
			if (((String) value).isEmpty()) {
				return null;
			}
		}

		String id = null;
		try {
			Method method = value.getClass().getMethod("getId");
			Object obj = method.invoke(value, new Object[] {});
			if (obj != null) {
				id = obj.toString();
				uiComponent.getAttributes().put(id, value);
			}
		} catch (Exception ex) {
			Logger.getLogger(GenericEntityConverter.class.getName()).log(Level.SEVERE, null, ex);
		}

		return id;

	}

	private Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

}