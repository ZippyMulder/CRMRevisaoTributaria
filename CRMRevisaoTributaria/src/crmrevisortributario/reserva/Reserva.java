package crmrevisortributario.reserva;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import crmrevisortributario.cliente.Cliente;
import crmrevisortributario.usuario.Usuario;


@Entity
@Table(name = "t_crt_reserva")
public class Reserva implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6611235946548271184L;

	/*Chaves compostas*/
	@EmbeddedId
	private ReservaPK reservaPK;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "T_CRT_USUARIO_cd_usuario", referencedColumnName = "cd_usuario", insertable = false, updatable = false)
	@ForeignKey(name = "T_CRT_RESERVA_T_CRT_USUARIO_FK")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "T_CRT_CLIENTE_cd_cliente", referencedColumnName = "cd_cliente", insertable = false, updatable = false)
	@ForeignKey(name = "T_CRT_RESERVA_T_CRT_CLIENTE_FK")
	private Cliente cliente;
	
	@Column(name = "dt_reserva", nullable = false)
	private Date dataReserva;

	@Column(name = "ds_situacao", nullable = false)
	private Boolean situacao;
	
	@Column(name = "ds_sub_representante", nullable = false, length = 250)
	private String subRepresentante;

	public ReservaPK getReservaPK() {
		return reservaPK;
	}

	public void setReservaPK(ReservaPK reservaPK) {
		this.reservaPK = reservaPK;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	public String getSubRepresentante() {
		return subRepresentante;
	}

	public void setSubRepresentante(String subRepresentante) {
		this.subRepresentante = subRepresentante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result
				+ ((dataReserva == null) ? 0 : dataReserva.hashCode());
		result = prime * result
				+ ((reservaPK == null) ? 0 : reservaPK.hashCode());
		result = prime * result
				+ ((situacao == null) ? 0 : situacao.hashCode());
		result = prime
				* result
				+ ((subRepresentante == null) ? 0 : subRepresentante.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (dataReserva == null) {
			if (other.dataReserva != null)
				return false;
		} else if (!dataReserva.equals(other.dataReserva))
			return false;
		if (reservaPK == null) {
			if (other.reservaPK != null)
				return false;
		} else if (!reservaPK.equals(other.reservaPK))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		if (subRepresentante == null) {
			if (other.subRepresentante != null)
				return false;
		} else if (!subRepresentante.equals(other.subRepresentante))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
}
