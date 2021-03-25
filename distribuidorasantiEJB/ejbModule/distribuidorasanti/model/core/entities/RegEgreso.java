package distribuidorasanti.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the reg_egresos database table.
 * 
 */
@Entity
@Table(name="reg_egresos")
@NamedQuery(name="RegEgreso.findAll", query="SELECT r FROM RegEgreso r")
public class RegEgreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_egresos")
	private Integer idEgresos;

	@Column(name="fecha_consulta")
	private String fechaConsulta;

	@Column(name="total_egresos")
	private double totalEgresos;

	public RegEgreso() {
	}

	public Integer getIdEgresos() {
		return this.idEgresos;
	}

	public void setIdEgresos(Integer idEgresos) {
		this.idEgresos = idEgresos;
	}

	public String getFechaConsulta() {
		return this.fechaConsulta;
	}

	public void setFechaConsulta(String fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public double getTotalEgresos() {
		return this.totalEgresos;
	}

	public void setTotalEgresos(double totalEgresos) {
		this.totalEgresos = totalEgresos;
	}

}