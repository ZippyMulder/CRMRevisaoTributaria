package crmrevisortributario.credito;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import crmrevisortributario.cliente.Cliente;

@Entity
@Table(name = "t_crt_credito")
public class Credito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5869996556907979801L;

	@Id
	@GeneratedValue
	@Column(name = "cd_credito")
	private Integer	           codigo;
	
	@Column(name = "dt_periodo_inicial", nullable = false)
	private Date	           periodoInicial;
	
	@Column(name = "dt_periodo_final", nullable = false)
	private Date	           periodoFinal;
	
	@Column(name = "vl_total_pis", nullable = false)
	private Float	           totalPis;
	
	@Column(name = "vl_total_cofins", nullable = false)
	private Float	           totalCofins;
	
	@Column(name = "vl_credito_bruto", nullable = true)
	private Float	           creditoBruto;
	
	@Column(name = "vl_credito_atualizado", nullable = false)
	private Float	           creditoEstimado;
	
	@OneToOne     
    @JoinColumn(name = "T_CRT_CLIENTE_cd_cliente")  
	private Cliente 		   cliente;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(Date periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public Date getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(Date periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	public Float getTotalPis() {
		return totalPis;
	}

	public void setTotalPis(Float totalPis) {
		this.totalPis = totalPis;
	}

	public Float getTotalCofins() {
		return totalCofins;
	}

	public void setTotalCofins(Float totalCofins) {
		this.totalCofins = totalCofins;
	}

	public Float getCreditoBruto() {
		return creditoBruto;
	}

	public void setCreditoBruto(Float creditoBruto) {
		this.creditoBruto = creditoBruto;
	}

	public Float getCreditoEstimado() {
		return creditoEstimado;
	}

	public void setCreditoEstimado(Float creditoEstimado) {
		this.creditoEstimado = creditoEstimado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime
				* result
				+ ((creditoEstimado == null) ? 0 : creditoEstimado
						.hashCode());
		result = prime * result
				+ ((creditoBruto == null) ? 0 : creditoBruto.hashCode());
		result = prime * result
				+ ((periodoFinal == null) ? 0 : periodoFinal.hashCode());
		result = prime * result
				+ ((periodoInicial == null) ? 0 : periodoInicial.hashCode());
		result = prime * result
				+ ((totalCofins == null) ? 0 : totalCofins.hashCode());
		result = prime * result
				+ ((totalPis == null) ? 0 : totalPis.hashCode());
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
		Credito other = (Credito) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (creditoEstimado == null) {
			if (other.creditoEstimado != null)
				return false;
		} else if (!creditoEstimado.equals(other.creditoEstimado))
			return false;
		if (creditoBruto == null) {
			if (other.creditoBruto != null)
				return false;
		} else if (!creditoBruto.equals(other.creditoBruto))
			return false;
		if (periodoFinal == null) {
			if (other.periodoFinal != null)
				return false;
		} else if (!periodoFinal.equals(other.periodoFinal))
			return false;
		if (periodoInicial == null) {
			if (other.periodoInicial != null)
				return false;
		} else if (!periodoInicial.equals(other.periodoInicial))
			return false;
		if (totalCofins == null) {
			if (other.totalCofins != null)
				return false;
		} else if (!totalCofins.equals(other.totalCofins))
			return false;
		if (totalPis == null) {
			if (other.totalPis != null)
				return false;
		} else if (!totalPis.equals(other.totalPis))
			return false;
		return true;
	}
	
}



