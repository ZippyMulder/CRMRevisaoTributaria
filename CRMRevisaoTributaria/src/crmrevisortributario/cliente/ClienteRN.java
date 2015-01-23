package crmrevisortributario.cliente;

import java.util.List;

import crmrevisortributario.util.DAOFactory;
import crmrevisortributario.util.RNException;
import crmrevisortributario.web.util.PermissaoUtil;

public class ClienteRN {

	private ClienteDAO	clienteDAO;
	
	public ClienteRN() {
		this.clienteDAO = DAOFactory.criarClienteDAO();
	}
	
	public void salvar(Cliente cliente) {

		Integer codigo = cliente.getCodigo();
		if (codigo == null || codigo == 0) {
			this.clienteDAO.salvar(cliente);
		} else {
			this.clienteDAO.atualizar(cliente);
		}
	}

	public void excluir(Cliente cliente) {
		this.clienteDAO.excluir(cliente);
	}

	public Cliente carregar(Integer codigo) {
		return this.clienteDAO.carregar(codigo);
	}

	public Cliente buscarPorCnpj(String cnpj) throws RNException {
		
		if (cnpj.trim().equals("") || cnpj == null)
		{
			throw new RNException("CNPJ não informado!");
		}
		
		Cliente cliente = this.clienteDAO.buscarPorCnpj(cnpj);
		if (cliente == null)
		{
			throw new RNException("Nenhum cliente encontrado!");
		}
		
		return cliente;
	}
	
	public List<Cliente> buscarPorRazaoSocial(String razaoSocial, Boolean buscarQualquerPosicao) throws RNException {
		
		if (razaoSocial.trim().equals("") || razaoSocial == null)
		{
			throw new RNException("Razão Social não informada!");
		}
		
		if (!PermissaoUtil.possuiPermissao(PermissaoUtil.PERSMISSAO_ADMINISTRADOR)){
			if (razaoSocial.length() < 5){
				throw new RNException("É necessário informar no mínimo 5 caracteres para realizar a pesquisa!");
			}
		}
		
		List<Cliente> listaCliente = this.clienteDAO.buscarPorRazaoSocial(razaoSocial, buscarQualquerPosicao);
		
		if (listaCliente == null || listaCliente.size() == 0)
		{
			throw new RNException("Nenhum cliente encontrado!");
		}
		
		return listaCliente;
	}
	
	public List<Cliente> listar() {
		return this.clienteDAO.listar();
	}
	
}