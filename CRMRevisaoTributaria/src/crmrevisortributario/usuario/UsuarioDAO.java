
package crmrevisortributario.usuario;

import java.util.List;

public interface UsuarioDAO {

	public void salvar(Usuario usuario);

	public void atualizar(Usuario usuario);

	public void excluir(Usuario usuario);
	
	public void enviarNovaSenha(String email);

	public Usuario carregar(Integer codigo);

	public Usuario buscarPorLogin(String login);
	
	public Usuario buscarPorEmail(String email);

	public List<Usuario> listar();
}
