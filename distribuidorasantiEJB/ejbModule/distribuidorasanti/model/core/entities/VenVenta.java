package distribuidorasanti.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ven_ventas database table.
 * 
 */
@Entity
@Table(name="ven_ventas")
@NamedQuery(name="VenVenta.findAll", query="SELECT v FROM VenVenta v")
public class VenVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_venta")
	private Integer idVenta;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ven")
	private Date fechaVen;

	@Column(name="ven_iva")
	private double venIva;

	@Column(name="ven_subtotal")
	private double venSubtotal;

	@Column(name="ven_total")
	private double venTotal;

	//bi-directional many-to-one association to VenDetalleVenta
	@OneToMany(mappedBy="venVenta")
	private List<VenDetalleVenta> venDetalleVentas;

	//bi-directional many-to-one association to VenCliente
	@ManyToOne
	@JoinColumn(name="cli_cedula")
	private VenCliente venCliente;

	public VenVenta() {
	}

	public Integer getIdVenta() {
		return this.idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public Date getFechaVen() {
		return this.fechaVen;
	}

	public void setFechaVen(Date fechaVen) {
		this.fechaVen = fechaVen;
	}

	public double getVenIva() {
		return this.venIva;
	}

	public void setVenIva(double venIva) {
		this.venIva = venIva;
	}

	public double getVenSubtotal() {
		return this.venSubtotal;
	}

	public void setVenSubtotal(double venSubtotal) {
		this.venSubtotal = venSubtotal;
	}

	public double getVenTotal() {
		return this.venTotal;
	}

	public void setVenTotal(double venTotal) {
		this.venTotal = venTotal;
	}

	public List<VenDetalleVenta> getVenDetalleVentas() {
		return this.venDetalleVentas;
	}

	public void setVenDetalleVentas(List<VenDetalleVenta> venDetalleVentas) {
		this.venDetalleVentas = venDetalleVentas;
	}

	public VenDetalleVenta addVenDetalleVenta(VenDetalleVenta venDetalleVenta) {
		getVenDetalleVentas().add(venDetalleVenta);
		venDetalleVenta.setVenVenta(this);

		return venDetalleVenta;
	}

	public VenDetalleVenta removeVenDetalleVenta(VenDetalleVenta venDetalleVenta) {
		getVenDetalleVentas().remove(venDetalleVenta);
		venDetalleVenta.setVenVenta(null);

		return venDetalleVenta;
	}

	public VenCliente getVenCliente() {
		return this.venCliente;
	}

	public void setVenCliente(VenCliente venCliente) {
		this.venCliente = venCliente;
	}

}