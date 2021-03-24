package distribuidorasanti.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_producto database table.
 * 
 */
@Entity
@Table(name="inv_producto")
@NamedQuery(name="InvProducto.findAll", query="SELECT i FROM InvProducto i")
public class InvProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_producto")
	private Integer codProducto;

	@Temporal(TemporalType.DATE)
	@Column(name="pro_fecha_compra")
	private Date proFechaCompra;

	@Column(name="pro_nombre")
	private String proNombre;

	@Column(name="pro_presio")
	private double proPresio;

	@Column(name="pro_presio_ad")
	private double proPresioAd;

	private Integer stock;

	//bi-directional many-to-one association to InvDistribuidore
	@ManyToOne
	@JoinColumn(name="id_distribuidor")
	private InvDistribuidore invDistribuidore;

	//bi-directional many-to-one association to InvMarca
	@ManyToOne
	@JoinColumn(name="id_marca")
	private InvMarca invMarca;

	//bi-directional many-to-one association to VenDetalleVenta
	@OneToMany(mappedBy="invProducto")
	private List<VenDetalleVenta> venDetalleVentas;

	public InvProducto() {
	}

	public Integer getCodProducto() {
		return this.codProducto;
	}

	public void setCodProducto(Integer codProducto) {
		this.codProducto = codProducto;
	}

	public Date getProFechaCompra() {
		return this.proFechaCompra;
	}

	public void setProFechaCompra(Date proFechaCompra) {
		this.proFechaCompra = proFechaCompra;
	}

	public String getProNombre() {
		return this.proNombre;
	}

	public void setProNombre(String proNombre) {
		this.proNombre = proNombre;
	}

	public double getProPresio() {
		return this.proPresio;
	}

	public void setProPresio(double proPresio) {
		this.proPresio = proPresio;
	}

	public double getProPresioAd() {
		return this.proPresioAd;
	}

	public void setProPresioAd(double proPresioAd) {
		this.proPresioAd = proPresioAd;
	}

	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public InvDistribuidore getInvDistribuidore() {
		return this.invDistribuidore;
	}

	public void setInvDistribuidore(InvDistribuidore invDistribuidore) {
		this.invDistribuidore = invDistribuidore;
	}

	public InvMarca getInvMarca() {
		return this.invMarca;
	}

	public void setInvMarca(InvMarca invMarca) {
		this.invMarca = invMarca;
	}

	public List<VenDetalleVenta> getVenDetalleVentas() {
		return this.venDetalleVentas;
	}

	public void setVenDetalleVentas(List<VenDetalleVenta> venDetalleVentas) {
		this.venDetalleVentas = venDetalleVentas;
	}

	public VenDetalleVenta addVenDetalleVenta(VenDetalleVenta venDetalleVenta) {
		getVenDetalleVentas().add(venDetalleVenta);
		venDetalleVenta.setInvProducto(this);

		return venDetalleVenta;
	}

	public VenDetalleVenta removeVenDetalleVenta(VenDetalleVenta venDetalleVenta) {
		getVenDetalleVentas().remove(venDetalleVenta);
		venDetalleVenta.setInvProducto(null);

		return venDetalleVenta;
	}

}