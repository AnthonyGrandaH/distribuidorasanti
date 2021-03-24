package distribuidorasanti.controller.inventario;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import distribuidorasanti.controller.JSFUtil;
import distribuidorasanti.model.core.entities.InvDistribuidore;
import distribuidorasanti.model.inventario.managers.ManagerDistribuidor;



@Named
@SessionScoped
public class BeanDistribuidor implements Serializable{
	@EJB
	private ManagerDistribuidor managerDistribuidor;
	private List<InvDistribuidore> listaDistribuidor;
	private InvDistribuidore nuevoDistribuidor;
	private InvDistribuidore edicionDistribuidor;

	public BeanDistribuidor() {
		// TODO Auto-generated constructor stub
	}

	public String actionMenuDistribuidor() {
		listaDistribuidor = managerDistribuidor.findAllDistribuidores();
		return "distribuidores";
	}
	
	public String actionMenuNuevoDistribuidor() {
		nuevoDistribuidor = new InvDistribuidore();
		return "distribuidor_nuevo";
	}
	
	public void actionListenerInsertarNuevoDistribuidor() {
		try {
			managerDistribuidor.insertarDistribuidor(nuevoDistribuidor);
			listaDistribuidor = managerDistribuidor.findAllDistribuidores();
			JSFUtil.crearMensajeINFO("Distribuidor Creado");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String actionSeleccionarEdicionDistribuidor(InvDistribuidore distribuidor) {
		edicionDistribuidor = distribuidor;
		return "distribuidor_edicion";
	}
	
	public void actionListenerActualizarEdicionDistribuidor() {
		try {
			managerDistribuidor.actualizarDistribuidor(edicionDistribuidor);
			listaDistribuidor = managerDistribuidor.findAllDistribuidores();
			JSFUtil.crearMensajeINFO("Distribuidor actualizado");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarDistribuidor(int idDistribuidor) {
		try {
			managerDistribuidor.eliminarDistribuidor(idDistribuidor);
			listaDistribuidor = managerDistribuidor.findAllDistribuidores();
			JSFUtil.crearMensajeINFO("Distribuidor eliminado");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<InvDistribuidore> getListaDistribuidor() {
		return listaDistribuidor;
	}

	public void setListaDistribuidor(List<InvDistribuidore> listaDistribuidor) {
		this.listaDistribuidor = listaDistribuidor;
	}

	public InvDistribuidore getNuevoDistribuidor() {
		return nuevoDistribuidor;
	}

	public void setNuevoDistribuidor(InvDistribuidore nuevoDistribuidor) {
		this.nuevoDistribuidor = nuevoDistribuidor;
	}

	public InvDistribuidore getEdicionDistribuidor() {
		return edicionDistribuidor;
	}

	public void setEdicionDistribuidor(InvDistribuidore edicionDistribuidor) {
		this.edicionDistribuidor = edicionDistribuidor;
	}
	

	
	
	
}
