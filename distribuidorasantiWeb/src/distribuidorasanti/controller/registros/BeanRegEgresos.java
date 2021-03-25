package distribuidorasanti.controller.registros;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import distribuidorasanti.controller.JSFUtil;
import distribuidorasanti.model.core.entities.InvProducto;
import distribuidorasanti.model.core.entities.RegEgreso;
import distribuidorasanti.model.core.entities.RegIngreso;
import distribuidorasanti.model.core.entities.VenVenta;
import distribuidorasanti.model.registro.managers.ManagerEgresos;
import distribuidorasanti.model.registro.managers.ManagerRegIngreso;
import distribuidorasanti.model.registros.dto.RegistroEgresosDTO;
import distribuidorasanti.model.registros.dto.RegistroIngresosDTO;
import distribuidorasanti.model.ventas.dtos.ProductoDto;

@Named
@SessionScoped
public class BeanRegEgresos implements Serializable {
	@EJB
	ManagerEgresos mEgreso;
	private List<RegistroEgresosDTO> listadoEgresos;
	private List<RegistroEgresosDTO> listadoRespaldo;
	private RegistroEgresosDTO nuevoEgreso;
	private double total = 0;
	private int anioEgreso;
	private int mesEgreso;

	public BeanRegEgresos() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct

	public void inicializar() {
		listadoEgresos = mEgreso.findAllRegistroEgresosDTO();
		listadoRespaldo = listadoEgresos;
	}

	public String actionMenuEgresos() {
		listadoEgresos = mEgreso.findAllRegistroEgresosDTO();
		listadoRespaldo = listadoEgresos;
		return "egresos";
	}

	public void actionListenerBuscarRegistroPorFecha() {
		System.out.println("Mes Ingresado " + mesEgreso);
		System.out.println("Año Ingresado" + anioEgreso);
		if (anioEgreso == 0) {
			listadoEgresos = listadoRespaldo;
			total = 0;
		} else {
			listadoEgresos = listadoRespaldo;
			listadoEgresos = mEgreso.findAllRegistroByFecha(listadoEgresos, mesEgreso, anioEgreso);
			total = mEgreso.getPrecioTotal();
			JSFUtil.crearMensajeINFO("Busqueda Completada");
		}
	}

	public void actionListenerCrearRegistroEgreso() {
		try {
			if (total == 0) {
				JSFUtil.crearMensajeERROR("No se puede Generar el Reporte: Total:0");
			} else {
				RegEgreso egresoNuevo = new RegEgreso();
				egresoNuevo.setFechaConsulta(mesEgreso+"/"+ anioEgreso);
				egresoNuevo.setTotalEgresos(total);
				mEgreso.insertarEgresos(egresoNuevo);
				listadoEgresos = mEgreso.findAllRegistroEgresosDTO();
				listadoRespaldo = listadoEgresos;
				JSFUtil.crearMensajeINFO("Registro Ingresado Existosamente");
			}
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}

	}

	public List<RegistroEgresosDTO> getListadoEgresos() {
		return listadoEgresos;
	}

	public void setListadoEgresos(List<RegistroEgresosDTO> listadoEgresos) {
		this.listadoEgresos = listadoEgresos;
	}

	public List<RegistroEgresosDTO> getListadoRespaldo() {
		return listadoRespaldo;
	}

	public void setListadoRespaldo(List<RegistroEgresosDTO> listadoRespaldo) {
		this.listadoRespaldo = listadoRespaldo;
	}

	public RegistroEgresosDTO getNuevoEgreso() {
		return nuevoEgreso;
	}

	public void setNuevoEgreso(RegistroEgresosDTO nuevoEgreso) {
		this.nuevoEgreso = nuevoEgreso;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getAnioEgreso() {
		return anioEgreso;
	}

	public void setAnioEgreso(int anioEgreso) {
		this.anioEgreso = anioEgreso;
	}

	public int getMesEgreso() {
		return mesEgreso;
	}

	public void setMesEgreso(int mesEgreso) {
		this.mesEgreso = mesEgreso;
	}

}
