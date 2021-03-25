package distribuidorasanti.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the reg_ingresos database table.
 * 
 */
@Entity
@Table(name="reg_ingresos")
@NamedQuery(name="RegIngreso.findAll", query="SELECT r FROM RegIngreso r")
public class RegIngreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ingresos")
	private Integer idIngresos;

	private String fechaconsulta;

	@Column(name="total_ingresos")
	private double totalIngresos;

	public RegIngreso() {
	}

	public Integer getIdIngresos() {
		return this.idIngresos;
	}

	public void setIdIngresos(Integer idIngresos) {
		this.idIngresos = idIngresos;
	}

	public String getFechaconsulta() {
		return this.fechaconsulta;
	}

	public void setFechaconsulta(String fechaconsulta) {
		this.fechaconsulta = fechaconsulta;
	}

	public double getTotalIngresos() {
		return this.totalIngresos;
	}

	public void setTotalIngresos(double totalIngresos) {
		this.totalIngresos = totalIngresos;
	}

}