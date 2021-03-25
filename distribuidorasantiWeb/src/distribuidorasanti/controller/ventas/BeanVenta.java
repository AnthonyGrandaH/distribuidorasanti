package distribuidorasanti.controller.ventas;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import distribuidorasanti.model.ventas.managers.ManagerVenDetalleVenta;
import distribuidorasanti.model.ventas.managers.ManagerVenVenta;

@Named
@SessionScoped
public class BeanVenta implements Serializable {

	@EJB
	ManagerVenVenta mVenta;
	@EJB
	ManagerVenDetalleVenta mDetalleVenta;
	@EJB
	ManagerInventario mProducto;

	private InvProducto nuevoProducto;
	private VenVenta nuevaVenta;
	private List<ProductoDto> carrito;
	

	public BeanVenta() {
		// TODO Auto-generated constructor stub
	}

	public boolean actionListenerCrearVenta(VenCliente nuevoCliente, List<ProductoDto> carrito,
			double precioCantidadxProducto ,double precioIva,double precioTotal ) {
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
				System.out.println(""+nuevaVenta.getVenTotal());
				System.out.println(""+nuevoCliente.getCliCedula());
				mVenta.insertarCabeceraVenta(nuevaVenta, nuevoCliente.getCliCedula());
				InvProducto producto;
				int UltimaIdVenta = mVenta.findUltimaVenta();
				for (int i = 0; i < carrito.size(); i++) {

					VenDetalleVenta nuevoDetalleVenta = new VenDetalleVenta();
					nuevoDetalleVenta.setCantidadDt(carrito.get(i).getCantidad());
					nuevoDetalleVenta.setTotalDt(carrito.get(i).getPresioTotal());
					mDetalleVenta.insertarVentaDetalle(nuevoDetalleVenta, UltimaIdVenta,
							carrito.get(i).getCodProducto());
					
					producto=mProducto.findProductoByCod(carrito.get(i).getCodProducto());
					producto.setStock(producto.getStock() - carrito.get(i).getCantidad() );
					mProducto.actualizarProducto(producto, producto.getInvDistribuidore().getIdDistribuidor(), producto.getInvMarca().getIdMarca());
				}
				JSFUtil.crearMensajeINFO("Venta Realizada");
				return true;
		
 
			}
		
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();

		}
		return false;
	}

	


	public List<ProductoDto> getCarrito() {
		return carrito;
	}

	public void setCarrito(List<ProductoDto> carrito) {
		this.carrito = carrito;
	}



}
