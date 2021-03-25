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
	private List<RegistroIngresosDTO> listadoIngresosDTO;
	private List< RegistroIngresosDTO> listadoRespaldo;
	private List <RegIngreso> listadoIngreso;
 private RegistroIngresosDTO nuevoIngreso;
 private double total=0;
 private int anioIngreso;
 private int mesIngreso;
	public BeanRegIngresos() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	
	
	
	public void inicializar() {
		listadoIngresosDTO = mIngreso.findAllRegistroIngresoDTO();
		listadoRespaldo=listadoIngresosDTO;
	}

	public String actionMenuIngresos() {
		listadoIngresosDTO = mIngreso.findAllRegistroIngresoDTO();
		listadoRespaldo=listadoIngresosDTO;
		return "ingresos";
	}
public String actionMenuRegistroIngresos() {
	listadoIngreso=mIngreso.findAllIngresos();
	return "registroingresos";
}
	
	public void actionListenerBuscarRegistroPorFecha() {
		System.out.println("Mes Ingresado "+mesIngreso);
	    System.out.println("Año Ingresado"+ anioIngreso);
		if(anioIngreso==0) {
			listadoIngresosDTO=listadoRespaldo;
			total=0;
		}else {
			listadoIngresosDTO=listadoRespaldo;
			listadoIngresosDTO=mIngreso.findAllRegistroByFecha(listadoIngresosDTO, mesIngreso, anioIngreso);
			total=mIngreso.getPrecioTotal();
			JSFUtil.crearMensajeINFO("Busqueda Completada");
		}
	}
	
	public void actionListenerCrearRegistroIngreso() {
		try {
			if(total==0) {
				JSFUtil.crearMensajeERROR("No se puede Generar el Reporte: Total:0");
			}else {
			RegIngreso ingresoNuevo=new RegIngreso();
			ingresoNuevo.setFechaconsulta(mesIngreso+"/"+anioIngreso);
			ingresoNuevo.setTotalIngresos(total);
			mIngreso.insertarIngreso(ingresoNuevo);
			listadoIngresosDTO = mIngreso.findAllRegistroIngresoDTO();
			listadoRespaldo=listadoIngresosDTO;
			JSFUtil.crearMensajeINFO("Registro Ingresado Existosamente");
			}
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	

	public List<RegistroIngresosDTO> getListadoIngresosDTO() {
		return listadoIngresosDTO;
	}

	public void setListadoIngresosDTO(List<RegistroIngresosDTO> listadoIngresosDTO) {
		this.listadoIngresosDTO = listadoIngresosDTO;
	}

	public List<RegIngreso> getListadoIngreso() {
		return listadoIngreso;
	}

	public void setListadoIngreso(List<RegIngreso> listadoIngreso) {
		this.listadoIngreso = listadoIngreso;
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
