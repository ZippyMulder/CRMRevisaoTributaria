package crmrevisortributario.usuario;

import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;

import org.springframework.util.DigestUtils;

import crmrevisortributario.util.DAOFactory;
import crmrevisortributario.util.RNException;
import crmrevisortributario.util.UtilException;
import crmrevisortributario.web.util.EmailUtil;
import crmrevisortributario.web.util.PermissaoUtil;

public class UsuarioRN {

	private UsuarioDAO	usuarioDAO;

	public UsuarioRN() {
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}

	public void salvar(Usuario usuario, String tipo, String confirmarSenha, String senhaCriptografada) throws RNException {
		
		Integer codigo = usuario.getCodigo();
		
		//Valida preenchimento da página
		if (usuario.getNome() == null || usuario.getNome().equals("")) {
			throw new RNException("Nome não informado");
		}
		
		if (usuario.getLogin() == null || usuario.getLogin().equals("")) {
			throw new RNException("Login não informado");
		}
		
		if (codigo == null || codigo == 0){
			if (usuario.getSenha() == null || usuario.getSenha().equals("")) {
				throw new RNException("Senha não informada");
			}
		}
		
		if (codigo == null || codigo == 0){
			if (confirmarSenha == null || confirmarSenha.equals("")) {
				throw new RNException("Confirmação da Senha não informada");
			}
		}
		
		if (usuario.getEmail() == null || usuario.getEmail().equals("")) {
			throw new RNException("E-mail não informado");
		}
		
		EmailUtil emailUtil = new EmailUtil();
		if (emailUtil.validarEmail(usuario.getEmail()) == false){
			throw new RNException("E-mail inválido");
		}
		
		String senha = usuario.getSenha();
		if (codigo == null || codigo == 0){
			if (!senha.equals(confirmarSenha)) {
				throw new RNException("Verifique o campo Confirmar Senha, senha não confirmada corretamente");
			}
		}
		
		//Encriptografar a senha----------------------------------------------------------------------		
		if (senha != null && senha.trim().length() == 0 && (codigo != null && codigo != 0)) {
			usuario.setSenha(senhaCriptografada);
		} else {
			String senhaCripto = DigestUtils.md5DigestAsHex(senha.getBytes());
			usuario.setSenha(senhaCripto);
		}
		//---------------------------------------------------------------------------------------------

		//Recupera as permissões do usuário
		if (codigo != null && codigo != 0){
			UsuarioRN usuarioRN = new UsuarioRN();
			usuario.setPermissao(usuarioRN.carregar(usuario.getCodigo()).getPermissao());
			
			if (usuario.getPermissao() == null || usuario.getPermissao().size() == 0){
				atribuiPermissao(usuario, PermissaoUtil.PERSMISSAO_USUARIO);
			}
			
			if (tipo.equals(PermissaoUtil.PERSMISSAO_ADMINISTRADOR)){
				atribuiPermissao(usuario, PermissaoUtil.PERSMISSAO_ADMINISTRADOR);
			}else{
				removePermissao(usuario,PermissaoUtil.PERSMISSAO_ADMINISTRADOR);
			}
			
		}else{
			if (tipo.equals(PermissaoUtil.PERSMISSAO_ADMINISTRADOR)){
				atribuiPermissao(usuario, PermissaoUtil.PERSMISSAO_ADMINISTRADOR);
			}
		}
		

		if (codigo == null || codigo == 0) {

			usuario.getPermissao().add("ROLE_USUARIO");

			this.usuarioDAO.salvar(usuario);
			
		} else {
			this.usuarioDAO.atualizar(usuario);
		}
	}

	public void excluir(Usuario usuario) {
		
		this.usuarioDAO.excluir(usuario);
	}
	
	public void enviarNovaSenha(String email) throws RNException
	{
		
		if (email.trim().equals("")){
			throw new RNException("E-mail não informado!");
		}
		
		EmailUtil emailUtil = new EmailUtil();
		if (emailUtil.validarEmail(email) == false){
			throw new RNException("E-mail informado inválido!");
		}
		
		//Gera a nova senha e criptografa
		String senhaNova = gerarNovaSenha();
		String senhaCripto = DigestUtils.md5DigestAsHex(senhaNova.getBytes());
		
		//Envia senha por email
		try{
			emailUtil.enviarEmail(null, email, "Alteração de Senha DOTBUILD - CRM Revisor Tributário", "Nova senha: " + senhaNova);
		}catch(UtilException e){
			
		}
		
		Usuario usuario = this.buscarPorEmail(email);
		usuario.setSenha(senhaCripto);
		
		this.usuarioDAO.atualizar(usuario);
		
	}
	
	public Usuario carregar(Integer codigo) {
		return this.usuarioDAO.carregar(codigo);
	}

	public Usuario buscarPorLogin(String login) {
		return this.usuarioDAO.buscarPorLogin(login);
	}
	
	public Usuario buscarPorEmail(String email) {
		return this.usuarioDAO.buscarPorEmail(email);
	}
	
	public List<Usuario> listar() {
		return this.usuarioDAO.listar();
	}
	

	private String gerarNovaSenha()
	{
		
		String[] carct = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
							"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", 
							"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", 
							"u", "v", "w", "x", "y", "z", "A", "B", "C", "D", 
							"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", 
							"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", 
							"Y", "Z" }; 
		String senha = ""; 
		
		for (int x = 0; x < 10; x++) { 
			int j = (int) (Math.random() * carct.length); 
			senha += carct[j]; 
		} 
		
		return senha;

	}
	
	private String atribuiPermissao(Usuario usuario, String permissao) {

		Set<String> permissoes = usuario.getPermissao();

		if (permissoes.contains(permissao)) {
			permissoes.remove(permissao);
		} else {
			permissoes.add(permissao);
		}
		return null;
	}
	
	private String removePermissao(Usuario usuario, String permissao) {

		Set<String> permissoes = usuario.getPermissao();

		if (permissoes.contains(permissao)) {
			permissoes.remove(permissao);
		}
		return null;
	}
	
}