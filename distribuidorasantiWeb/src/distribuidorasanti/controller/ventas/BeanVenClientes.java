package distribuidorasanti.controller.ventas;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import distribuidorasanti.controller.JSFUtil;
import distribuidorasanti.model.core.entities.VenCliente;
import distribuidorasanti.model.ventas.managers.ManagerVenClientes;



@Named
@SessionScoped
public class BeanVenClientes implements Serializable {
  @EJB
  private ManagerVenClientes managerVenClentes;
	private List<VenCliente> listaVenClientes;
	private List<VenCliente> clientes;
	private VenCliente nuevoCliente;
	private VenCliente edicionCliente;
	private String cedula;
	private int idCliente;
	public BeanVenClientes() {
		
	}
	//Codigo de implemetacion para naveacion en las vistas
	
	public String actionMenuCliente() {
		listaVenClientes = managerVenClentes.findAllVenClientes();
		return "clientes";
	}
	
	public String actionMenuNuevoCliente() {
		nuevoCliente = new VenCliente();
		return "cliente_nuevo";
	}
	
	public String actionSeleccionarEdicionCliente(VenCliente cliente) {
		edicionCliente = cliente;
		return "cliente_edicion";
	}
	//Codigo de ejecucion para las vistas
	public void actionListenerInsertarNuevoCliente() {
		try {
			managerVenClentes.insertarClientes(nuevoCliente);
			listaVenClientes = managerVenClentes.findAllVenClientes();
			JSFUtil.crearMensajeINFO("Nuevo Cliente Creado"+nuevoCliente.getCliNombre());
			
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerActualizarEdicionCliente() {
		try {
			managerVenClentes.actualizarClientes(edicionCliente);
			listaVenClientes = managerVenClentes.findAllVenClientes();
			JSFUtil.crearMensajeINFO("Actualizada la informacion de cliente"+edicionCliente.getCliNombre());
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerEliminarCliente(String cedula) {
		try {
			managerVenClentes.eliminaCliente(cedula);
			listaVenClientes = managerVenClentes.findAllVenClientes();
			JSFUtil.crearMensajeINFO("Cliente: "+ cedula +"eliminado correctamente");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerBuscarCLiente() {
		try {
			System.out.println("cedula: "+cedula);
			nuevoCliente = new VenCliente();
			nuevoCliente = managerVenClentes.buscarClientebyCI(cedula);
		    cedula="";
			JSFUtil.crearMensajeINFO("Cliente encontrado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/*
	public void actionListenerBuscarCLiente(String cedula) {
		try {
			nuevoCliente = managerVenClentes.buscarClientebyCI(cedula);
			JSFUtil.crearMensajeINFO("Cliente encontrado");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}*/
	
	
	
	///Metodos acesores
	public List<VenCliente> getListaVenClientes() {
		return listaVenClientes;
	}
	public void setListaVenClientes(List<VenCliente> listaVenClientes) {
		this.listaVenClientes = listaVenClientes;
	}
	public VenCliente getNuevoCliente() {
		return nuevoCliente;
	}
	public void setNuevoCliente(VenCliente nuevoCliente) {
		this.nuevoCliente = nuevoCliente;
	}
	public VenCliente getEdicionCliente() {
		return edicionCliente;
	}
	public void setEdicionCliente(VenCliente edicionCliente) {
		this.edicionCliente = edicionCliente;
	}

	public List<VenCliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<VenCliente> clientes) {
		this.clientes = clientes;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	
}
