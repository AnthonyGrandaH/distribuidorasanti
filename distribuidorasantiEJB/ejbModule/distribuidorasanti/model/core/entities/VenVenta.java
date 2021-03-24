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

	@Column(name="ven_ciudad")
	private String venCiudad;

	@Column(name="ven_cuota_pago")
	private double venCuotaPago;

	@Column(name="ven_iva")
	private double venIva;

	@Column(name="ven_plazo")
	private Integer venPlazo;

	@Column(name="ven_subtotal")
	private double venSubtotal;

	@Column(name="ven_tipo_pago")
	private String venTipoPago;

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

	public String getVenCiudad() {
		return this.venCiudad;
	}

	public void setVenCiudad(String venCiudad) {
		this.venCiudad = venCiudad;
	}

	public double getVenCuotaPago() {
		return this.venCuotaPago;
	}

	public void setVenCuotaPago(double venCuotaPago) {
		this.venCuotaPago = venCuotaPago;
	}

	public double getVenIva() {
		return this.venIva;
	}

	public void setVenIva(double venIva) {
		this.venIva = venIva;
	}

	public Integer getVenPlazo() {
		return this.venPlazo;
	}

	public void setVenPlazo(Integer venPlazo) {
		this.venPlazo = venPlazo;
	}

	public double getVenSubtotal() {
		return this.venSubtotal;
	}

	public void setVenSubtotal(double venSubtotal) {
		this.venSubtotal = venSubtotal;
	}

	public String getVenTipoPago() {
		return this.venTipoPago;
	}

	public void setVenTipoPago(String venTipoPago) {
		this.venTipoPago = venTipoPago;
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