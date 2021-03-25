package distribuidorasanti.model.registros.dto;

import java.util.Date;

public class RegistroIngresosDTO {

	private int idVenta;
	private int idDtVenta;
	private double venTotal;
	private String cedula;
	private Date fechaVen;
	
	public RegistroIngresosDTO(int idVenta, int idDtVenta, double venTotal, String cedula, Date fechaVen) {
		super();
		this.idVenta = idVenta;
		this.idDtVenta = idDtVenta;
		this.venTotal = venTotal;
		this.cedula = cedula;
		this.fechaVen = fechaVen;
	}
	
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public int getIdDtVenta() {
		return idDtVenta;
	}
	public void setIdDtVenta(int idDtVenta) {
		this.idDtVenta = idDtVenta;
	}
	public double getVenTotal() {
		return venTotal;
	}
	public void setVenTotal(double venTotal) {
		this.venTotal = venTotal;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public Date getFechaVen() {
		return fechaVen;
	}
	public void setFechaVen(Date fechaVen) {
		this.fechaVen = fechaVen;
	}
	
	
	
}
