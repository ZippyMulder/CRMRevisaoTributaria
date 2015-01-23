package crmrevisortributario.reserva;

import java.util.List;

import crmrevisortributario.listagem.ListagemCliente;

public interface ReservaDAO {

	public void salvar(Reserva reserva);

	public void atualizar(Reserva reserva);
	
	public void excluir(Reserva reserva);

	public Reserva carregar(ReservaPK reservaPK);
	
	public Reserva carregarPorCliente(Integer codigo);
	
	public List<ListagemCliente> carregarListagemReservasUsuario(Integer codigo);

	public List<Reserva> listar();
}
