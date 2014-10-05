package br.com.bs.sistema.wrapper;

import br.com.bs.fw.enumeration.FilterType;
import br.com.bs.fw.util.GenericWrapper;
import br.com.bs.sistema.entity.User;

public class UserWrapper extends GenericWrapper<User> {
	
	public UserWrapper(){
		addSelectFilter("id", "Código", FilterType.Long);
		addSelectFilter("nome", "Nome", FilterType.String);
	}
}
