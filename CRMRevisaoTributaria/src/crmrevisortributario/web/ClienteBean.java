
package crmrevisortributario.web;

import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import crmrevisortributario.cliente.Cliente;
import crmrevisortributario.cliente.ClienteRN;
import crmrevisortributario.credito.Credito;
import crmrevisortributario.credito.CreditoRN;
import crmrevisortributario.listagem.ListagemCliente;
import crmrevisortributario.reserva.Reserva;
import crmrevisortributario.reserva.ReservaPK;
import crmrevisortributario.reserva.ReservaRN;
import crmrevisortributario.usuario.Usuario;
import crmrevisortributario.usuario.UsuarioRN;
import crmrevisortributario.util.RNException;
import crmrevisortributario.web.util.ContextoUtil;
import crmrevisortributario.web.util.PermissaoUtil;




@ManagedBean(name="clienteBean")
@ViewScoped
public class ClienteBean {

	private Cliente 		cliente	= new Cliente();
	private Credito 		credito	= new Credito();
	private Reserva 		reserva = new Reserva();
	private String 			campoPesquisa;	
	private Integer			tipoPesquisa = 1;	//Guarda o tipo de pesquisa 1 - CNPJ, 2 - Razão Social
	private List<Cliente>	lista;
	
	private List<ListagemCliente> listagemReserva;
	private List<Usuario> listaRepresentante;
	private Usuario 	  representante;
	private ReservaPK	  reservaPK;
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Boolean getexisteCodigoCliente()
	{
		if (cliente == null){
			return false;
		}else if (this.cliente.getCodigo() != null)
		{
			return true;
		}else{
			return false;
		}
	}
	
	public Credito getCredito() {
		return credito;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}

	public Reserva getReserva() {
		return reserva;
	}
	
	public boolean getSituacao()
	{
		if (reserva == null){
			return false;
		}else if (reserva.getSituacao() == null){
			return false;
		}else {
			return reserva.getSituacao();
		}
	}
	

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public String getCampoPesquisa() {
		return campoPesquisa;
	}

	public void setCampoPesquisa(String campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}

	public Integer getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(Integer tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public List<Cliente> getLista() {	

		return lista;
	}
	
	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}
	
	public int getlistaSize()
	{
		if (this.lista != null)
		{
			return this.lista.size();
		}else{
			return 0;
		}
	}
	
	public List<ListagemCliente> getListagemReserva() {
		return listagemReserva;
	}

	public void setListagemReserva(List<ListagemCliente> listagemReserva) {
		this.listagemReserva = listagemReserva;
	}
	
	
	public List<Usuario> getListaRepresentante() {
		return listaRepresentante;
	}

	public void setListaRepresentante(List<Usuario> listaRepresentante) {
		this.listaRepresentante = listaRepresentante;
	}
	
	public int getSizelistagemReserva()
	{
		if (this.listagemReserva != null)
		{
			return this.listagemReserva.size();
		}else{
			return 0;
		}
	}

	public Usuario getRepresentante() {
		
		return this.representante; 
		
		/*if (this.contaAtiva == null) {
			Usuario usuario = this.getUsuarioLogado();

			ContaRN contaRN = new ContaRN();
			this.contaAtiva = contaRN.buscarFavorita(usuario);

			if (this.contaAtiva == null) {
				List<Conta> contas = contaRN.listar(usuario);
				if (contas != null) {
					for (Conta conta : contas) {
						this.contaAtiva = conta;
						break;
					}
				}
			}
		}
		return this.contaAtiva;*/
	}
	
	public void setRepresentante(ValueChangeEvent event) {

		Integer usuario = (Integer) event.getNewValue();

		UsuarioRN usuarioRN = new UsuarioRN();
		this.representante = usuarioRN.carregar(usuario);
		
		pesquisarRepresentante();
		
		
	}

	public ReservaPK getReservaPK() {
		return reservaPK;
	}

	public void setReservaPK(ReservaPK reservaPK) {
		this.reservaPK = reservaPK;
	}

	public void setRepresentante(Usuario representante) {
		this.representante = representante;
	}

	/*Pesquisa_Cliente===================================================================================*/	
	public void iniciar()
	{
		/*Para que passe apenas uma vez */
		if (this.reserva == null){
			this.reserva = new Reserva();
		}
	}
	
	public void pesquisarCliente()
	{
		if (tipoPesquisa != null){
		
			FacesContext context = FacesContext.getCurrentInstance();
			ClienteRN clienteRN = new ClienteRN();
			this.reserva = null;
			
			if (tipoPesquisa == 1){
				try {
					
					this.cliente = clienteRN.buscarPorCnpj(campoPesquisa);
					
					CreditoRN creditoRN = new CreditoRN();
					this.credito = creditoRN.buscarCreditoCliente(this.cliente);
					
					exibirDetalhes();
					
				} catch (RNException e) {
					
					FacesMessage facesMessage = new FacesMessage(e.getMessage());
					facesMessage.setSeverity(facesMessage.SEVERITY_WARN);
					context.addMessage(null, facesMessage);
					
					this.lista = null;
					this.cliente = null;
					this.credito = null;
					this.reserva = null;
				}
			}else if (tipoPesquisa == 2){
				try {
					
					boolean adminstrador = PermissaoUtil.possuiPermissao(PermissaoUtil.PERSMISSAO_ADMINISTRADOR);
					if (adminstrador == true){
						lista = clienteRN.buscarPorRazaoSocial(campoPesquisa, true);
					}else{
						lista = clienteRN.buscarPorRazaoSocial(campoPesquisa, false);
					}
					
					if (lista.size() == 1)
					{
						this.cliente = lista.get(0);
						exibirDetalhes();
					}
					
				} catch (RNException e) {
					
					FacesMessage facesMessage = new FacesMessage(e.getMessage());
					facesMessage.setSeverity(facesMessage.SEVERITY_WARN);
					context.addMessage(null, facesMessage);
					
					this.lista = null;
					this.cliente = null;
					this.credito = null;
					this.reserva = null;
				}
			}
			
		}
		
		
	}
	
	public void reservarCliente()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		
		//Cria chave primaria da reserva
		ReservaPK reservaPK = new ReservaPK();
		reservaPK.setCliente(this.cliente.getCodigo());
		reservaPK.setUsuario(contextoBean.getUsuarioLogado().getCodigo());
		
		//Seta os dados da Reserva
		Reserva reservaCliente = new Reserva();
		reservaCliente.setReservaPK(reservaPK);
		reservaCliente.setUsuario(contextoBean.getUsuarioLogado());
		reservaCliente.setCliente(this.cliente);
		
		Calendar dataReserva = Calendar.getInstance();		
		reservaCliente.setDataReserva(dataReserva.getTime());
		reservaCliente.setSituacao(true);
		
		reservaCliente.setSubRepresentante(this.reserva.getSubRepresentante());
		
		//Efetua a reserva
		ReservaRN reservaRN = new ReservaRN();
		try{
			reservaRN.salvar(reservaCliente);
		}catch(RNException e){
			FacesMessage facesMessage = new FacesMessage(e.getMessage());
			facesMessage.setSeverity(facesMessage.SEVERITY_WARN);
			context.addMessage(null, facesMessage);
			return;
		}
		
		//Recarrega os dados da reserva
		this.reserva = reservaRN.carregar(reservaPK);
		
		//Exibe mensagem de confirmação
		FacesMessage facesMessage = new FacesMessage("Reserva efetuada com sucesso!");
		facesMessage.setSeverity(facesMessage.SEVERITY_INFO);
		context.addMessage(null, facesMessage);
		
	}
	
	public void exibirDetalhes()
	{
		CreditoRN creditoRN = new CreditoRN();
		this.credito = creditoRN.buscarCreditoCliente(this.cliente);
			
		ReservaRN reservaRN = new ReservaRN();					
		this.reserva = reservaRN.carregarPorCliente(this.cliente.getCodigo());
	}
	
	
	
	/*Listagem-cliente =================================================================================*/
	public void iniciarListagem_Cliente()
	{
	
		if (this.representante == null)
		{
			
			if (PermissaoUtil.possuiPermissao(PermissaoUtil.PERSMISSAO_ADMINISTRADOR)){
				UsuarioRN usuarioRN = new UsuarioRN();
				this.listaRepresentante = usuarioRN.listar();
				this.representante = listaRepresentante.get(0);
			}else{
				this.representante = ContextoUtil.getContextoBean().getUsuarioLogado(); 
			}
			
			//Carrega Reservas do usuario logado
			pesquisarRepresentante();
		}
	}
	
	public void pesquisarRepresentante()
	{
		
		this.reservaPK = new ReservaPK();
		
		//Carrega Reservas do usuario logado
		ReservaRN reservaRN = new ReservaRN();
		this.listagemReserva = reservaRN.carregarListagemReservasUsuario(this.representante.getCodigo());		
	}
	
	public void cancelarReserva()
	{
		
		ReservaRN reservaRN = new ReservaRN();
		
		Reserva reserva = new Reserva();
		reserva = reservaRN.carregar(this.reservaPK);
		
		reservaRN.excluir(reserva);
		pesquisarRepresentante();
		
	}
	
	
}
