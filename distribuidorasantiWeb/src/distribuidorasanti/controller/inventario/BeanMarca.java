package distribuidorasanti.controller.inventario;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import distribuidorasanti.controller.JSFUtil;
import distribuidorasanti.model.core.entities.InvMarca;
import distribuidorasanti.model.inventario.managers.ManagerMarca;



@Named
@SessionScoped
public class BeanMarca implements Serializable {

	@EJB
	private ManagerMarca managerMarca;
	private List<InvMarca> listaMarca;
	private InvMarca nuevaMarca;
	private InvMarca edicionMarca;
	
	public BeanMarca() {
		// TODO Auto-generated constructor stub
	}
	
	public String actionMenuNuevaMarca() {
		nuevaMarca = new InvMarca();
		return "marca_nueva";
	}
	
	public String actionMenuMarca() {
		listaMarca = managerMarca.findAllMarca();
		return "marca";
	}
	
	public void actionListenerInsertarNuevaMarca() {
		try {
			managerMarca.insertarMarca(nuevaMarca);
			listaMarca = managerMarca.findAllMarca();
			nuevaMarca = new InvMarca();
			JSFUtil.crearMensajeINFO("Marca Creada");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public String actionSeleccionarEdicionMarca(InvMarca marca) {
		edicionMarca = marca;
		return "marca_edicion";
	}
	
	public void actionListenerActualizarEdicionMarca() {
		try {
			managerMarca.actualizarMarca(edicionMarca);
			listaMarca = managerMarca.findAllMarca();
			JSFUtil.crearMensajeINFO("Marca Actualizada");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public void actionListenerEliminarMarca(int idMarca) {
		try {
			managerMarca.eliminarMarca(idMarca);
			listaMarca = managerMarca.findAllMarca();
			JSFUtil.crearMensajeINFO("Marca eliminada");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public InvMarca getNuevaMarca() {
		return nuevaMarca;
	}

	public void setNuevaMarca(InvMarca nuevaMarca) {
		this.nuevaMarca = nuevaMarca;
	}

	public List<InvMarca> getListaMarca() {
		return listaMarca;
	}

	public void setListaMarca(List<InvMarca> listaMarca) {
		this.listaMarca = listaMarca;
	}

	public InvMarca getEdicionMarca() {
		return edicionMarca;
	}

	public void setEdicionMarca(InvMarca edicionMarca) {
		this.edicionMarca = edicionMarca;
	}
		
}
