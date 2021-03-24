package distribuidorasanti.controller.inventario;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import distribuidorasanti.controller.JSFUtil;
import distribuidorasanti.model.core.entities.InvDistribuidore;
import distribuidorasanti.model.core.entities.InvMarca;
import distribuidorasanti.model.core.entities.InvProducto;
import distribuidorasanti.model.inventario.managers.ManagerDistribuidor;
import distribuidorasanti.model.inventario.managers.ManagerInventario;
import distribuidorasanti.model.inventario.managers.ManagerMarca;



@Named
@SessionScoped
public class BeanInventario implements Serializable {
	@EJB
	private ManagerInventario mProducto;
	@EJB
	private ManagerDistribuidor mDistribuidor;
	@EJB
	private ManagerMarca mMarca;
	private int idDistribuidor;
	private int idMarca;
	private List<InvProducto> listaProductos;
	private List<InvMarca> listaMarcas;
	private List<InvDistribuidore> listaDistribuidor;
	private InvProducto nuevoProducto;
	private InvProducto edicionProducto;
	
	public BeanInventario() {

	}
	
	public String actionMenuProductos() {
		listaProductos=mProducto.findAllProductos();
		return "productos";
	}
	
	
	
	public String actionMenuNuevoProducto() {
		nuevoProducto=new InvProducto();
		listaMarcas=mMarca.findAllMarca();
		listaDistribuidor=mDistribuidor.findAllDistribuidores();

		return "producto_nuevo";
	}
	
	public void actionListenerInsertarNuevoProducto() {
		try {
			mProducto.insertarProducto(nuevoProducto, idDistribuidor, idMarca);
			listaProductos=mProducto.findAllProductos();
			nuevoProducto=new InvProducto();
			JSFUtil.crearMensajeINFO("Producto Ingresado Exitosamente.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String actionSeleccionarEdicionProducto(InvProducto producto) {
		edicionProducto=producto;
		return "producto_edicion";
	}
	
	public void actionListenerActualizarEdicionProducto() {
		try {
			mProducto.actualizarProducto(edicionProducto, idDistribuidor, idMarca);
			listaProductos=mProducto.findAllProductos();
			JSFUtil.crearMensajeINFO("Producto Actualizado Exitosamente.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarProducto(int idProducto) {
		try {
			mProducto.eliminarProducto(idProducto);
			listaProductos=mProducto.findAllProductos();
			JSFUtil.crearMensajeINFO("Producto Eliminado Exitosamente.");
	
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public int getIdDistribuidor() {
		return idDistribuidor;
	}

	public void setIdDistribuidor(int idDistribuidor) {
		this.idDistribuidor = idDistribuidor;
	}

	public int getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public List<InvProducto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<InvProducto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<InvMarca> getListaMarcas() {
		return listaMarcas;
	}

	public void setListaMarcas(List<InvMarca> listaMarcas) {
		this.listaMarcas = listaMarcas;
	}

	public List<InvDistribuidore> getListaDistribuidor() {
		return listaDistribuidor;
	}

	public void setListaDistribuidor(List<InvDistribuidore> listaDistribuidor) {
		this.listaDistribuidor = listaDistribuidor;
	}

	public InvProducto getNuevoProducto() {
		return nuevoProducto;
	}

	public void setNuevoProducto(InvProducto nuevoProducto) {
		this.nuevoProducto = nuevoProducto;
	}

	public InvProducto getEdicionProducto() {
		return edicionProducto;
	}

	public void setEdicionProducto(InvProducto edicionProducto) {
		this.edicionProducto = edicionProducto;
	}

	
	
}
