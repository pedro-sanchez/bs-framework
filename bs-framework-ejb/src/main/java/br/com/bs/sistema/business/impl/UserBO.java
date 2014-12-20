package br.com.bs.sistema.business.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.bs.fw.business.iface.IMailBO;
import br.com.bs.fw.business.impl.GenericBO;
import br.com.bs.fw.util.ObjectUtil;
import br.com.bs.sistema.business.iface.IUserBO;
import br.com.bs.sistema.entity.User;
import br.com.bs.sistema.repository.UserDAO;

@Local
@Stateless
public class UserBO extends GenericBO<User, UserDAO> implements IUserBO {

	@EJB
	private IMailBO mailBO;
	
	@Override
	public User findByLoginAndSenha(String login, String senha){
		return getDao().findByLoginAndSenha(login, senha);
	}
	
	@Override
	public User findByEmail(String email){
		return getDao().findByEmail(email);
	}

	private String generatePassword(){
		UUID uuid = UUID.randomUUID();  
		String random = uuid.toString();  
		return random.substring(0,6); 
	}
	
	private String generateNewPassword(User entity){
		String userPassword = generatePassword();
		try {
			entity.setSenha(ObjectUtil.toMD5(userPassword));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			//throw new Exception("Falha ao criptografar Senha");
		}
		
		return userPassword;
	}
	
	@Override
	public User save(User entity) {
		Boolean isNew = entity.getId() == null;
		String userPassword = null;
		if(isNew){
			userPassword = generateNewPassword(entity);
		}
		entity = super.save(entity);
		
		if (!ObjectUtil.isEmpty(userPassword)) {
			newUserMail(entity, userPassword);
		}
		return entity;
	}
	

	public void newUserMail(User entity, String userPassword){
		
		//obter nome do sistema pelo arquivo de properties
		StringBuilder mensagemEmail =  new StringBuilder();

		mensagemEmail.append("Olá ");
		mensagemEmail.append(entity.getNome());
		mensagemEmail.append(",\n\n");
		
		mensagemEmail.append("Seu acesso foi criado sucesso!\n\n");

		mensagemEmail.append("Usuário: ");
		mensagemEmail.append(entity.getLogin());
		mensagemEmail.append("\n");

		mensagemEmail.append("Senha: ");
		mensagemEmail.append(userPassword);
		mensagemEmail.append("\n\n");

		mensagemEmail.append("Atenciosamente,\n");
		mensagemEmail.append("Equipe FutureGo");
		
		mailBO.sendMessage(entity.getEmail(), "Criação de usuário", mensagemEmail.toString());

	}

	
	@Override
	public void forgot(String email){
		User user = findByEmail(email);
		
		if(ObjectUtil.isEmpty(user)){
			return;
		}
		
		String userPassword = generateNewPassword(user);
		
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
		mensagemEmail.append(userPassword);
		mensagemEmail.append("\n\n");

		mensagemEmail.append("Atenciosamente,\n");
		mensagemEmail.append("Equipe FutureGo");
		
		mailBO.sendMessage(email, "Recuperação de Senha", mensagemEmail.toString());
		
	}
	
	@Override
	public void reset(Long entityId) {
		User user = findBy(entityId);
		
		if(ObjectUtil.isEmpty(user)){
			return;
		}
		
		String userPassword = generateNewPassword(user);
		
		//obter nome do sistema pelo arquivo de properties
		StringBuilder mensagemEmail =  new StringBuilder();

		mensagemEmail.append("Olá ");
		mensagemEmail.append(user.getNome());
		mensagemEmail.append(",\n\n");
		
		mensagemEmail.append("Seu acesso foi modificado!\n\n");

		mensagemEmail.append("Usuário: ");
		mensagemEmail.append(user.getLogin());
		mensagemEmail.append("\n");

		mensagemEmail.append("Senha: ");
		mensagemEmail.append(userPassword);
		mensagemEmail.append("\n\n");

		mensagemEmail.append("Atenciosamente,\n");
		mensagemEmail.append("Equipe FutureGo");
		
		mailBO.sendMessage(user.getEmail(), "Regeração de Senha", mensagemEmail.toString());

	}
}
