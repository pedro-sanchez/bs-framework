package br.com.bs.sistema.mb;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.bs.fw.business.iface.IMailBO;
import br.com.bs.fw.enumeration.WindowModeEnum;
import br.com.bs.fw.mb.MBGeneric;
import br.com.bs.fw.util.ObjectUtil;
import br.com.bs.sistema.business.iface.IUserBO;
import br.com.bs.sistema.entity.User;
import br.com.bs.sistema.search.UserSearch;

@ManagedBean(name="mbUser")
@ViewScoped
public class MBUser extends MBGeneric<User, IUserBO> implements Serializable{

	private static final long serialVersionUID = -2433515164743414832L;
	
	@EJB
	private IUserBO userBO;
	
	@EJB
	private IMailBO mailBO;
	
	public void init() {
		this.setWrapper(new UserSearch());
		setBo(userBO);
	}
	
	private String generatePassword(){
		UUID uuid = UUID.randomUUID();  
		String random = uuid.toString();  
		return random.substring(0,6); 
	}
	
	@Override
	protected void saveBO() {
		String userPassword = null;
		if(WindowModeEnum.NEW.equals(getMode())){
			userPassword = generatePassword();
			try {
				getEntity().setSenha(ObjectUtil.toMD5(userPassword));
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				addErrorMessage("Falha ao criptografar Senha");
			}
		}
		super.saveBO();
		
		if (!ObjectUtil.isEmpty(userPassword)) {
			newUserMail(userPassword);
		}
	}
	
	public void newUserMail(String userPassword){
		
		//obter nome do sistema pelo arquivo de properties
		StringBuilder mensagemEmail =  new StringBuilder();

		mensagemEmail.append("Olá ");
		mensagemEmail.append(getEntity().getNome());
		mensagemEmail.append(",\n\n");
		
		mensagemEmail.append("Seu acesso foi criado sucesso!\n\n");

		mensagemEmail.append("Usuário: ");
		mensagemEmail.append(getEntity().getLogin());
		mensagemEmail.append("\n");

		mensagemEmail.append("Senha: ");
		mensagemEmail.append(userPassword);
		mensagemEmail.append("\n\n");

		mensagemEmail.append("Atenciosamente,\n");
		mensagemEmail.append("Equipe FutureGo");
		
		mailBO.sendMessage(getEntity().getEmail(), "Criação de usuário", mensagemEmail.toString());

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
		mensagemEmail.append("Equipe FutureGo");
		
		mailBO.sendMessage(email, "Recuperação de Senha", mensagemEmail.toString());

	}

	
	
}