
package crmrevisortributario.web;

import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.util.DigestUtils;

import crmrevisortributario.usuario.Usuario;
import crmrevisortributario.usuario.UsuarioRN;
import crmrevisortributario.util.RNException;
import crmrevisortributario.web.util.ContextoUtil;
import crmrevisortributario.web.util.PermissaoUtil;


@ManagedBean(name="usuarioBean")
@RequestScoped
public class UsuarioBean {

	private Usuario	    	usuario	= new Usuario();
	private String	       	confirmarSenha;
	private String	       	destinoSalvar; //Controla para qual pagina o usuário será direcionado
	private String			tipo;
	
	private List<Usuario>	lista;		   //Será utilizado na tela de listagem de usuários	
	private String 			senhaCriptografada;
	
	private Integer			processo;	  //1 - Incluir 2 - Alterar		

	public String novo() {
		this.destinoSalvar = "/restrito/cadastro_usuario";
		this.usuario = new Usuario();
		this.processo = 1;
		return "/restrito/cadastro_usuario";
	}

	public void editar() {
		this.processo = 2;
		this.senhaCriptografada = this.usuario.getSenha();
		this.confirmarSenha = this.usuario.getSenha();
		if (this.usuario.getPermissao().contains(PermissaoUtil.PERSMISSAO_ADMINISTRADOR)){
			this.setTipo(PermissaoUtil.PERSMISSAO_ADMINISTRADOR);
		}else{
			this.setTipo(PermissaoUtil.PERSMISSAO_USUARIO);
		}
	}

	public String salvar() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		UsuarioRN usuarioRN = new UsuarioRN();
		
		try {
			
			usuarioRN.salvar(this.usuario,this.tipo,this.confirmarSenha, this.senhaCriptografada);
			
			if (this.processo == 1){
				FacesMessage facesMessage = new FacesMessage("Usuário cadastrado com sucesso");
				facesMessage.setSeverity(facesMessage.SEVERITY_INFO);
				context.addMessage(null, facesMessage);
			}else{
				FacesMessage facesMessage = new FacesMessage("Usuário alterado com sucesso");
				facesMessage.setSeverity(facesMessage.SEVERITY_INFO);
				context.addMessage(null, facesMessage);
			}
			
			if (PermissaoUtil.possuiPermissao(PermissaoUtil.PERSMISSAO_ADMINISTRADOR)){
				novo();
			}

			return this.destinoSalvar;
			
		} catch (RNException e) {
			FacesMessage facesMessage = new FacesMessage(e.getMessage());
			facesMessage.setSeverity(facesMessage.SEVERITY_WARN);
			context.addMessage(null, facesMessage);
			return null;
		}
		
	}

	public String excluir() {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuario);
		this.lista = null;
		return null;
	}

	public List<Usuario> getLista() {
		if (this.lista == null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			this.lista = usuarioRN.listar();
		}
		return this.lista;
	}
	


	public void enviarNovaSenha() {
		
		FacesContext context = FacesContext.getCurrentInstance();

		UsuarioRN usuarioRN = new UsuarioRN();
		
		try{
			usuarioRN.enviarNovaSenha(this.usuario.getEmail());
			
			FacesMessage facesMessage = new FacesMessage("Foi enviado um email para: " + this.usuario.getEmail() + " contendo a nova senha");
			facesMessage.setSeverity(facesMessage.SEVERITY_INFO);
			context.addMessage(null, facesMessage);
		}catch(RNException e){
			FacesMessage facesMessage = new FacesMessage(e.getMessage());
			facesMessage.setSeverity(facesMessage.SEVERITY_WARN);
			context.addMessage(null, facesMessage);
		}
		
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSenhaCriptografada() {
		return senhaCriptografada;
	}

	public void setSenhaCriptografada(String senhaCriptografada) {
		this.senhaCriptografada = senhaCriptografada;
	}

	public Integer getProcesso() {
		return processo;
	}

	public void setProcesso(Integer processo) {
		this.processo = processo;
	}
	

	
	
}
