package distribuidorasanti.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the inv_distribuidores database table.
 * 
 */
@Entity
@Table(name="inv_distribuidores")
@NamedQuery(name="InvDistribuidore.findAll", query="SELECT i FROM InvDistribuidore i")
public class InvDistribuidore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_distribuidor")
	private Integer idDistribuidor;

	@Column(name="direccion_dist")
	private String direccionDist;

	@Column(name="nombre_dist")
	private String nombreDist;

	@Column(name="telefono_dist")
	private String telefonoDist;

	//bi-directional many-to-one association to InvProducto
	@OneToMany(mappedBy="invDistribuidore")
	private List<InvProducto> invProductos;

	public InvDistribuidore() {
	}

	public Integer getIdDistribuidor() {
		return this.idDistribuidor;
	}

	public void setIdDistribuidor(Integer idDistribuidor) {
		this.idDistribuidor = idDistribuidor;
	}

	public String getDireccionDist() {
		return this.direccionDist;
	}

	public void setDireccionDist(String direccionDist) {
		this.direccionDist = direccionDist;
	}

	public String getNombreDist() {
		return this.nombreDist;
	}

	public void setNombreDist(String nombreDist) {
		this.nombreDist = nombreDist;
	}

	public String getTelefonoDist() {
		return this.telefonoDist;
	}

	public void setTelefonoDist(String telefonoDist) {
		this.telefonoDist = telefonoDist;
	}

	public List<InvProducto> getInvProductos() {
		return this.invProductos;
	}

	public void setInvProductos(List<InvProducto> invProductos) {
		this.invProductos = invProductos;
	}

	public InvProducto addInvProducto(InvProducto invProducto) {
		getInvProductos().add(invProducto);
		invProducto.setInvDistribuidore(this);

		return invProducto;
	}

	public InvProducto removeInvProducto(InvProducto invProducto) {
		getInvProductos().remove(invProducto);
		invProducto.setInvDistribuidore(null);

		return invProducto;
	}

}