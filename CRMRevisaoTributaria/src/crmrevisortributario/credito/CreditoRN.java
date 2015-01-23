package crmrevisortributario.credito;

import crmrevisortributario.cliente.Cliente;
import crmrevisortributario.util.DAOFactory;

public class CreditoRN {

	private CreditoDAO	CreditoDAO;
	
	public CreditoRN() {
		this.CreditoDAO = DAOFactory.criarCreditoDAO();
	}

	public Credito buscarCreditoCliente(Cliente cliente) {			
		return this.CreditoDAO.buscarCreditoCliente(cliente);
	}
	
}