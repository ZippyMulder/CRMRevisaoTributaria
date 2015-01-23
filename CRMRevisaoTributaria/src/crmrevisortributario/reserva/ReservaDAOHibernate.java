
package crmrevisortributario.reserva;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import crmrevisortributario.listagem.ListagemCliente;


public class ReservaDAOHibernate implements ReservaDAO {

	private Session	session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Reserva reserva) {
		this.session.save(reserva);
		this.session.flush();
		this.session.clear();
	}

	public void atualizar(Reserva reserva) {
		this.session.update(reserva);
		this.session.flush();
		this.session.clear();
	}
	
	public void excluir(Reserva reserva) {
		this.session.delete(reserva);
		this.session.flush();
		this.session.clear();
	}

	public Reserva carregar(ReservaPK reservaPK) {
		return (Reserva) this.session.get(Reserva.class, reservaPK);
	}
	
	public Reserva carregarPorCliente(Integer codigo) {
		
		String hql = "select r from Reserva r where r.cliente = :cliente and r.situacao = :situacao";
		Query consulta = this.session.createQuery(hql);
		consulta.setInteger("cliente", codigo);
		consulta.setInteger("situacao", 1);

		return (Reserva) consulta.uniqueResult();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ListagemCliente> carregarListagemReservasUsuario(Integer codigo)
	{
		
		String hql = "select vlrc from ListagemCliente vlrc " + 
					 " where vlrc.usuario = :usuario and vlrc.situacao = :situacao " + 
					 " order by vlrc.dataReserva desc, vlrc.creditoEstimado desc ";
					  	
		Query consulta = this.session.createQuery(hql);
		consulta.setInteger("usuario", codigo);
		consulta.setInteger("situacao", 1);

		return  (List<ListagemCliente>) consulta.list();
		
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> listar() {
		return this.session.createCriteria(Reserva.class).list();
	}
}
