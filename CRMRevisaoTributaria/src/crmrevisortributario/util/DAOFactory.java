
package crmrevisortributario.util;


import crmrevisortributario.cliente.ClienteDAO;
import crmrevisortributario.cliente.ClienteDAOHibernate;
import crmrevisortributario.credito.CreditoDAO;
import crmrevisortributario.credito.CreditoDAOHibernate;
import crmrevisortributario.reserva.ReservaDAO;
import crmrevisortributario.reserva.ReservaDAOHibernate;
import crmrevisortributario.usuario.UsuarioDAO;
import crmrevisortributario.usuario.UsuarioDAOHibernate;

public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
	}
	
	public static ClienteDAO criarClienteDAO() {
		ClienteDAOHibernate clienteDAO = new ClienteDAOHibernate();
		clienteDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return clienteDAO;
	}
	
	public static CreditoDAO criarCreditoDAO() {
		CreditoDAOHibernate creditoDAO = new CreditoDAOHibernate();
		creditoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return creditoDAO;
	}
	
	public static ReservaDAO criarReservaDAO() {
		ReservaDAOHibernate reservaDAO = new ReservaDAOHibernate();
		reservaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return reservaDAO;
	}

}