package distribuidorasanti.controller.ventas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import distribuidorasanti.controller.JSFUtil;
import distribuidorasanti.model.core.entities.InvProducto;
import distribuidorasanti.model.core.entities.VenCliente;
import distribuidorasanti.model.core.entities.VenDetalleVenta;
import distribuidorasanti.model.core.entities.VenVenta;
import distribuidorasanti.model.inventario.managers.ManagerInventario;
import distribuidorasanti.model.ventas.dtos.ProductoDto;
import distribuidorasanti.model.ventas.managers.ManagerVenCarrito;
import distribuidorasanti.model.ventas.managers.ManagerVenDetalleVenta;
import distribuidorasanti.model.ventas.managers.ManagerVenVenta;

@Named
@SessionScoped
public class BeanVenCarrito implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerVenCarrito mCarrito;
	@EJB
	private ManagerVenVenta mVenta;
	@EJB
	ManagerVenDetalleVenta mDetalleVenta;
	@EJB
	ManagerInventario mProducto;
	private List<ProductoDto> listado;
	private List<ProductoDto> listadoRespaldo;
	private List<ProductoDto> carrito;
	private ProductoDto producto;
	private ProductoDto editarproducto;
	private int cantidad;
	private InvProducto nuevoProducto;
	private double precioCantidadxProducto = 0;
	private double precioIva = 0;
	private double precioTotal = 0;
	private String nombreProducto;
	private String nombreMarca;
	private VenVenta nuevaVenta;

	@PostConstruct

	public void inicializar() {
		listado = mCarrito.generarDatosProductos();
		listadoRespaldo = listado;
	}

	public String actionMenuCarrito() {
		return "Carrito";
	}

	public String actionMenuProductos() {
		if (carrito == null) {
			listado = mCarrito.generarDatosProductos();
			listadoRespaldo = listado;
		} else {
			listado = listadoRespaldo;
		}

		return "Ventas";
	}

	public String actionRegresarMenu() {
		carrito = null;
		listado = mCarrito.generarDatosProductos();
		listadoRespaldo = listado;
		precioCantidadxProducto = 0;
		precioIva = 0;
		precioTotal = 0;
		
		return "/menu";
	}

	public void actionListenerAgregerProducto(ProductoDto P) {
		try {
			carrito = mCarrito.agregarProductoCarrito(carrito, P);
			actionListenerGenerarSumatoria();
			JSFUtil.crearMensajeINFO("Producto agregado al carrito");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();

		}
	}

	public void actionListenerEliminarCliente(int codProducto) {
		try {
			mCarrito.eliminarProducto(codProducto, listado, carrito);
			actionListenerGenerarSumatoria();
			JSFUtil.crearMensajeINFO("Producto: " + codProducto + " eliminado correctamente");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerSeleccionarProducto(ProductoDto producto) {
		editarproducto = producto;
		System.out.println("Producto  seleccionado:" + editarproducto.getCodProducto());
	}

	public void actionListenerGenerarVenta(VenCliente nuevoCliente) {
		try {

			boolean confirmacionVenta;
			confirmacionVenta = actionListenerCrearVenta(nuevoCliente, carrito, precioCantidadxProducto, precioIva,
					precioTotal);
			if (confirmacionVenta) {
				carrito = null;
				listado = mCarrito.generarDatosProductos();
				listadoRespaldo = listado;
				JSFUtil.crearMensajeINFO("Venta Realizada");
				precioCantidadxProducto = 0;
				precioIva = 0;
				precioTotal = 0;
			} else {
				JSFUtil.crearMensajeERROR("Compra No realizada");
			}
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean actionListenerCrearVenta(VenCliente nuevoCliente, List<ProductoDto> carrito,
			double precioCantidadxProducto, double precioIva, double precioTotal) {
		try {
			if (nuevoCliente == null || carrito == null) {
				JSFUtil.crearMensajeERROR("Debe Ingresar el cliente solicitado y que haya \n "
						+ "seleccionado los productos en el carrito de compra");
				return false;
			} else {
				nuevaVenta = new VenVenta();
				nuevaVenta.setVenCliente(nuevoCliente);
				nuevaVenta.setFechaVen(new Date());
				nuevaVenta.setVenSubtotal(precioCantidadxProducto);
				nuevaVenta.setVenIva(precioIva);
				nuevaVenta.setVenTotal(precioTotal);
				nuevaVenta.setVenCliente(nuevoCliente);
				System.out.println("Total venta: " + nuevaVenta.getVenTotal());
				mVenta.insertarCabeceraVenta(nuevaVenta);

				int UltimaIdVenta = mVenta.findUltimaVenta();
				System.out.println("UltimaVenta: " + UltimaIdVenta);
				for (int i = 0; i < carrito.size(); i++) {

					VenDetalleVenta nuevoDetalleVenta = new VenDetalleVenta();
					nuevoDetalleVenta.setCantidadDt(carrito.get(i).getCantidad());
					nuevoDetalleVenta.setTotalDt(carrito.get(i).getPresioTotal());
					mDetalleVenta.insertarVentaDetalle(nuevoDetalleVenta, UltimaIdVenta,
							carrito.get(i).getCodProducto());

					nuevoProducto = mProducto.findProductoByCod(carrito.get(i).getCodProducto());
					nuevoProducto.setStock(nuevoProducto.getStock() - carrito.get(i).getCantidad());
					mProducto.actualizarProducto(nuevoProducto, nuevoProducto.getInvDistribuidore().getIdDistribuidor(),
							nuevoProducto.getInvMarca().getIdMarca());
				}
				return true;

			}

		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();

		}
		return false;
	}

	public void actionListenerBuscarProductoByNombre() {
		try {
			System.out.println("Nombre producto ingresado " + nombreProducto);
			listado = listadoRespaldo;
			if (nombreProducto.equals("")) {

			} else {
				listado = mCarrito.findProductoDtobyNombre(listado, nombreProducto);
			}

		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();

		}
	}

	public void actionListenerBuscarProductobyMarca() {
		try {
			System.out.println("Nombre producto ingresado " + nombreMarca);
			listado = listadoRespaldo;
			if (nombreProducto.equals("")) {

			} else {
				listado = mCarrito.findProductoDtobyMarca(listado, nombreMarca);
			}

		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();

		}
	}

	public void actionListenerGenerarSumatoria() {
		if (carrito != null) {
			precioCantidadxProducto = mCarrito.sumatotaldeProductos(carrito);
			precioIva = precioCantidadxProducto * (0.12);
			precioTotal = precioCantidadxProducto + precioIva;
		} else {
			precioCantidadxProducto = 0;
		}

	}

	public void actionListenerActualizarProducto() {
		try {

			if (editarproducto.getCantidadIngresada() == 0) {
				JSFUtil.crearMensajeERROR("Solo se ingresa cantidades mayores a 0");
			} else {
				mCarrito.actualizarCantidad(editarproducto, listado, carrito);
				actionListenerGenerarSumatoria();
				JSFUtil.crearMensajeINFO("Producto modificado correctamente");

			}

		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<ProductoDto> getListado() {
		return listado;
	}

	public void setListado(List<ProductoDto> listado) {
		this.listado = listado;
	}

	public List<ProductoDto> getCarrito() {
		return carrito;
	}

	public void setCarrito(List<ProductoDto> carrito) {
		this.carrito = carrito;
	}

	public ProductoDto getProducto() {
		return producto;
	}

	public void setProducto(ProductoDto producto) {
		this.producto = producto;
	}

	public ProductoDto getEditarproducto() {
		return editarproducto;
	}

	public void setEditarproducto(ProductoDto editarproducto) {
		this.editarproducto = editarproducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getNombreMarca() {
		return nombreMarca;
	}

	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

	public double getPrecioCantidadxProducto() {
		return precioCantidadxProducto;
	}

	public void setPrecioCantidadxProducto(double precioCantidadxProducto) {
		this.precioCantidadxProducto = precioCantidadxProducto;
	}

	public double getPrecioIva() {
		return precioIva;
	}

	public void setPrecioIva(double precioIva) {
		this.precioIva = precioIva;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

}
