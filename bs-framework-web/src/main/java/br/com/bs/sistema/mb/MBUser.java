package br.com.bs.sistema.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.bs.fw.mb.MBGeneric;
import br.com.bs.fw.util.ObjectUtil;
import br.com.bs.sistema.business.iface.IUserBO;
import br.com.bs.sistema.entity.User;
import br.com.bs.sistema.wrapper.UserWrapper;

@Named("mbUser")
@ViewScoped
public class MBUser extends MBGeneric<User, IUserBO> implements Serializable{

	private static final long serialVersionUID = -2433515164743414832L;
	
	@EJB
	private IUserBO userBO;
	
	public void init() {
		this.setWrapper(new UserWrapper());
		setBo(userBO);
	}
	
	public void forgotPassword(String email){

		User user = getBo().findByEmail(email);
		
		if(ObjectUtil.isEmpty(user)){
			return;
		}
		
		//obter nome do sistema pelo arquivo de properties
		StringBuilder mensagemEmail =  new StringBuilder();

		mensagemEmail.append("Olá ");
		mensagemEmail.append(user.getNome());
		mensagemEmail.append(",\n\n");
		
		mensagemEmail.append("Seu acesso foi solicitado!\n\n");

		mensagemEmail.append("Usuário: ");
		mensagemEmail.append(user.getLogin());
		mensagemEmail.append("\n");

		mensagemEmail.append("Senha: ");
		mensagemEmail.append(user.getSenha());
		mensagemEmail.append("\n\n");

		mensagemEmail.append("Atenciosamente,\n");
		mensagemEmail.append("Equipe BortoSan");
		
		//mailBO.sendMessage(email, "Recuperação de Senha", mensagemEmail.toString());

	}

	
	
}