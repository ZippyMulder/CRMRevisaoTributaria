
package crmrevisortributario.credito;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import crmrevisortributario.cliente.Cliente;


public class CreditoDAOHibernate implements CreditoDAO {

	private Session	session;

	public void setSession(Session session) {
		this.session = session;
	}

	public Credito buscarCreditoCliente(Cliente cliente) {
		Criteria criteria = this.session.createCriteria(Credito.class);
		criteria.add(Restrictions.eq("cliente", cliente));
		return (Credito) criteria.uniqueResult();
	}
	
}
