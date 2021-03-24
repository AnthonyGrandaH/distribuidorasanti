package distribuidorasanti.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ven_detalle_venta database table.
 * 
 */
@Entity
@Table(name="ven_detalle_venta")
@NamedQuery(name="VenDetalleVenta.findAll", query="SELECT v FROM VenDetalleVenta v")
public class VenDetalleVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_dt_venta")
	private Integer idDtVenta;

	@Column(name="cantidad_dt")
	private Integer cantidadDt;

	@Column(name="total_dt")
	private double totalDt;

	//bi-directional many-to-one association to InvProducto
	@ManyToOne
	@JoinColumn(name="cod_producto")
	private InvProducto invProducto;

	//bi-directional many-to-one association to VenVenta
	@ManyToOne
	@JoinColumn(name="id_venta")
	private VenVenta venVenta;

	public VenDetalleVenta() {
	}

	public Integer getIdDtVenta() {
		return this.idDtVenta;
	}

	public void setIdDtVenta(Integer idDtVenta) {
		this.idDtVenta = idDtVenta;
	}

	public Integer getCantidadDt() {
		return this.cantidadDt;
	}

	public void setCantidadDt(Integer cantidadDt) {
		this.cantidadDt = cantidadDt;
	}

	public double getTotalDt() {
		return this.totalDt;
	}

	public void setTotalDt(double totalDt) {
		this.totalDt = totalDt;
	}

	public InvProducto getInvProducto() {
		return this.invProducto;
	}

	public void setInvProducto(InvProducto invProducto) {
		this.invProducto = invProducto;
	}

	public VenVenta getVenVenta() {
		return this.venVenta;
	}

	public void setVenVenta(VenVenta venVenta) {
		this.venVenta = venVenta;
	}

}