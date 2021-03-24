package distribuidorasanti.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ven_clientes database table.
 * 
 */
@Entity
@Table(name="ven_clientes")
@NamedQuery(name="VenCliente.findAll", query="SELECT v FROM VenCliente v")
public class VenCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cli_cedula")
	private String cliCedula;

	@Column(name="cli_apellido")
	private String cliApellido;

	@Column(name="cli_direccion")
	private String cliDireccion;

	@Column(name="cli_nombre")
	private String cliNombre;

	@Column(name="cli_telefono")
	private String cliTelefono;

	//bi-directional many-to-one association to VenVenta
	@OneToMany(mappedBy="venCliente")
	private List<VenVenta> venVentas;

	public VenCliente() {
	}

	public String getCliCedula() {
		return this.cliCedula;
	}

	public void setCliCedula(String cliCedula) {
		this.cliCedula = cliCedula;
	}

	public String getCliApellido() {
		return this.cliApellido;
	}

	public void setCliApellido(String cliApellido) {
		this.cliApellido = cliApellido;
	}

	public String getCliDireccion() {
		return this.cliDireccion;
	}

	public void setCliDireccion(String cliDireccion) {
		this.cliDireccion = cliDireccion;
	}

	public String getCliNombre() {
		return this.cliNombre;
	}

	public void setCliNombre(String cliNombre) {
		this.cliNombre = cliNombre;
	}

	public String getCliTelefono() {
		return this.cliTelefono;
	}

	public void setCliTelefono(String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}

	public List<VenVenta> getVenVentas() {
		return this.venVentas;
	}

	public void setVenVentas(List<VenVenta> venVentas) {
		this.venVentas = venVentas;
	}

	public VenVenta addVenVenta(VenVenta venVenta) {
		getVenVentas().add(venVenta);
		venVenta.setVenCliente(this);

		return venVenta;
	}

	public VenVenta removeVenVenta(VenVenta venVenta) {
		getVenVentas().remove(venVenta);
		venVenta.setVenCliente(null);

		return venVenta;
	}

}