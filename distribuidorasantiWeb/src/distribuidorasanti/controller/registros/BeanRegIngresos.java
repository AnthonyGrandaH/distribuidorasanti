package distribuidorasanti.controller.registros;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import distribuidorasanti.controller.JSFUtil;
import distribuidorasanti.model.core.entities.InvProducto;
import distribuidorasanti.model.core.entities.RegIngreso;
import distribuidorasanti.model.core.entities.VenVenta;
import distribuidorasanti.model.registro.managers.ManagerRegIngreso;
import distribuidorasanti.model.registros.dto.RegistroIngresosDTO;
import distribuidorasanti.model.ventas.dtos.ProductoDto;

@Named
@SessionScoped
public class BeanRegIngresos implements Serializable {
 @EJB
 ManagerRegIngreso mIngreso;
	private List<RegistroIngresosDTO> listadoIngresos;
	private List< RegistroIngresosDTO> listadoRespaldo;
 private RegistroIngresosDTO nuevoIngreso;
 private double total=0;
 private int anioIngreso;
 private int mesIngreso;
	public BeanRegIngresos() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	
	
	
	public void inicializar() {
		listadoIngresos = mIngreso.findAllRegistroIngresoDTO();
		listadoRespaldo=listadoIngresos;
	}

	public String actionMenuIngresos() {
		listadoIngresos = mIngreso.findAllRegistroIngresoDTO();
		listadoRespaldo=listadoIngresos;
		return "ingresos";
	}

	
	public void actionListenerBuscarRegistroPorFecha() {
		System.out.println("Mes Ingresado "+mesIngreso);
	    System.out.println("Año Ingresado"+ anioIngreso);
		if(anioIngreso==0) {
			listadoIngresos=listadoRespaldo;
			total=0;
		}else {
			listadoIngresos=listadoRespaldo;
			listadoIngresos=mIngreso.findAllRegistroByFecha(listadoIngresos, mesIngreso, anioIngreso);
			total=mIngreso.getPrecioTotal();
			JSFUtil.crearMensajeINFO("Registro generado exitosamente");
		}
	}
	
	public void actionListenerCrearRegistroIngreso() {
		try {
			if(total==0) {
				JSFUtil.crearMensajeERROR("No se puede Generar el Reporte: Total:0");
			}else {
			RegIngreso ingresoNuevo=new RegIngreso();
			ingresoNuevo.setFechaconsulta(""+anioIngreso);
			ingresoNuevo.setTotalIngresos(total);
			mIngreso.insertarIngreso(ingresoNuevo);
			listadoIngresos = mIngreso.findAllRegistroIngresoDTO();
			listadoRespaldo=listadoIngresos;
			JSFUtil.crearMensajeINFO("Registro Ingresado Existosamente");
			}
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	public List<RegistroIngresosDTO> getListadoIngresos() {
		return listadoIngresos;
	}

	public void setListadoIngresos(List<RegistroIngresosDTO> listadoIngresos) {
		this.listadoIngresos = listadoIngresos;
	}

	public List<RegistroIngresosDTO> getListadoRespaldo() {
		return listadoRespaldo;
	}

	public void setListadoRespaldo(List<RegistroIngresosDTO> listadoRespaldo) {
		this.listadoRespaldo = listadoRespaldo;
	}

	public RegistroIngresosDTO getNuevoIngreso() {
		return nuevoIngreso;
	}

	public void setNuevoIngreso(RegistroIngresosDTO nuevoIngreso) {
		this.nuevoIngreso = nuevoIngreso;
	}



	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getAnioIngreso() {
		return anioIngreso;
	}

	public void setAnioIngreso(int anioIngreso) {
		this.anioIngreso = anioIngreso;
	}

	public int getMesIngreso() {
		return mesIngreso;
	}

	public void setMesIngreso(int mesIngreso) {
		this.mesIngreso = mesIngreso;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
