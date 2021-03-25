package distribuidorasanti.model.registros.dto;

import java.util.Date;

public class RegistroEgresosDTO {

	private int codProducto;
	private String proNombre;
	private double proPrecioAd;
	private Date proFechaCompra;
	
	public RegistroEgresosDTO(int codProducto, String proNombre, double proPrecioAd, Date proFechaCompra) {
		super();
		this.codProducto = codProducto;
		this.proNombre = proNombre;
		this.proPrecioAd = proPrecioAd;
		this.proFechaCompra = proFechaCompra;
	}

	public int getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
	}

	public String getProNombre() {
		return proNombre;
	}

	public void setProNombre(String proNombre) {
		this.proNombre = proNombre;
	}

	public double getProPrecioAd() {
		return proPrecioAd;
	}

	public void setProPrecioAd(double proPrecioAd) {
		this.proPrecioAd = proPrecioAd;
	}

	public Date getProFechaCompra() {
		return proFechaCompra;
	}

	public void setProFechaCompra(Date proFechaCompra) {
		this.proFechaCompra = proFechaCompra;
	}
	
	
}
