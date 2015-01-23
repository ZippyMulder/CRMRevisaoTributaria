package crmrevisortributario.reserva;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReservaPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 245751172165304826L;

	@Basic(optional = false)
	@Column(name = "T_CRT_USUARIO_cd_usuario", nullable = false)
	private Integer usuario;
	
	@Basic(optional = false)
	@Column(name = "T_CRT_CLIENTE_cd_cliente", nullable = false)
	private Integer cliente;
	
	public ReservaPK() {
    }

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
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
		ReservaPK other = (ReservaPK) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	

	
}

