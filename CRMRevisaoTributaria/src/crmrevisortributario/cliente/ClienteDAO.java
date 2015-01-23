package crmrevisortributario.cliente;

import java.util.List;

public interface ClienteDAO {

	public void salvar(Cliente cliente);

	public void atualizar(Cliente cliente);

	public void excluir(Cliente cliente);

	public Cliente carregar(Integer codigo);

	public Cliente buscarPorCnpj(String cnpj);
	
	public List<Cliente> buscarPorRazaoSocial(String razaoSocial, Boolean buscarQualquerPosicao);

	public List<Cliente> listar();
}
