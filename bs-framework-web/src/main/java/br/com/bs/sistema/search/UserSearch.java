package br.com.bs.sistema.search;

import br.com.bs.fw.enumeration.FilterType;
import br.com.bs.fw.util.GenericSearch;
import br.com.bs.fw.util.IGenericSearch;
import br.com.bs.sistema.entity.User;

public class UserSearch  extends GenericSearch<User> implements IGenericSearch<User> {
	
	public UserSearch(){
		addSelectFilter("id", "Código", FilterType.Long);
		addSelectFilter("nome", "Nome", FilterType.String);
	}
}
