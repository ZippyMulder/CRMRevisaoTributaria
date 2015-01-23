package crmrevisortributario.credito;

import crmrevisortributario.cliente.Cliente;


public interface CreditoDAO {

	public Credito buscarCreditoCliente(Cliente cliente);
	
}
