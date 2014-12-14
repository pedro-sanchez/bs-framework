package br.com.bs.sistema.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.bs.fw.mb.MBGeneric;
import br.com.bs.sistema.business.iface.IUserBO;
import br.com.bs.sistema.entity.User;
import br.com.bs.sistema.search.UserSearch;

@ManagedBean(name="mbUser")
@ViewScoped
public class MBUser extends MBGeneric<User, IUserBO> implements Serializable{

	private static final long serialVersionUID = -2433515164743414832L;
	
	@EJB
	private IUserBO userBO;
	
	public void init() {
		this.setWrapper(new UserSearch());
		setBo(userBO);
	}
	
}