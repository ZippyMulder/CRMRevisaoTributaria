package crmrevisortributario.reserva;

import java.util.List;

import crmrevisortributario.listagem.ListagemCliente;
import crmrevisortributario.util.DAOFactory;
import crmrevisortributario.util.RNException;

public class ReservaRN {

	private ReservaDAO	reservaDAO;
	
	public ReservaRN() {
		this.reservaDAO = DAOFactory.criarReservaDAO();
	}
	
	public void salvar(Reserva reserva) throws RNException {
		
		if (reserva.getSubRepresentante() == null || reserva.getSubRepresentante().trim().equals("")){
			throw new RNException("Sub Representante não informado!");
		}
		
		this.reservaDAO.salvar(reserva);
	
	}
	
	public void excluir(Reserva reserva){
		this.reservaDAO.excluir(reserva);
	}

	public Reserva carregar(ReservaPK reservaPK) {
		return this.reservaDAO.carregar(reservaPK);
	}
	
	public Reserva carregarPorCliente(Integer codigo) {
		return this.reservaDAO.carregarPorCliente(codigo);
	}
	
	public List<ListagemCliente> carregarListagemReservasUsuario(Integer codigo)
	{
		return this.reservaDAO.carregarListagemReservasUsuario(codigo);
	}
	
	public List<Reserva> listar() {
		return this.reservaDAO.listar();
	}
	
}