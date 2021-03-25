package distribuidorasanti.controller.ventas;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import distribuidorasanti.controller.JSFUtil;
import distribuidorasanti.model.ventas.dtos.ProductoDto;
import distribuidorasanti.model.ventas.managers.ManagerVenCarrito;


@Named
@SessionScoped
public class BeanVenCarrito implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerVenCarrito mCarrito;
	private List<ProductoDto> listado;
	private List<ProductoDto> listadoRespaldo;
	private List<ProductoDto> carrito;
    private ProductoDto producto;
    private ProductoDto editarproducto;
    private int cantidad;
    private double precioCantidadxProducto=0;
    private double precioIva=0;
    private double precioTotal=0;
    private String nombreProducto;
    private String nombreMarca;

	@PostConstruct
	public void inicializar() {
		listado = mCarrito.generarDatosProductos();
		listadoRespaldo=listado;
	}

	public String actionMenuCarrito() {
		return "Carrito";
	}

	public String actionMenuProductos() {
			listado =listadoRespaldo;
		
		
		return "Ventas";
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
			JSFUtil.crearMensajeINFO("Producto: "+ codProducto +" eliminado correctamente");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	 public void actionListenerSeleccionarProducto(ProductoDto producto) {
			editarproducto=producto;
			System.out.println("Producto  seleccionado:"+editarproducto.getCodProducto());
		}


	 
	 
	 
	 
	 public void actionListenerBuscarProductoByNombre() {
			try {
				System.out.println("Nombre producto ingresado "+nombreProducto );
				listado =listadoRespaldo;
				if(nombreProducto.equals("")) {
					
				}else {
					listado=  mCarrito.findProductoDtobyNombre(listado, nombreProducto);
				}
				
			
			} catch (Exception e) {
				JSFUtil.crearMensajeERROR(e.getMessage());
				e.printStackTrace();

			}
		}
	 
	 public void actionListenerBuscarProductobyMarca() {
			try {
				System.out.println("Nombre producto ingresado "+nombreMarca );
				listado =listadoRespaldo;
				if(nombreProducto.equals("")) {
					
				}else {
					listado=  mCarrito.findProductoDtobyMarca(listado, nombreMarca);
				}
				
			
			} catch (Exception e) {
				JSFUtil.crearMensajeERROR(e.getMessage());
				e.printStackTrace();

			}
		}
	 
	 public void actionListenerGenerarSumatoria() {
		 if(carrito!=null) {
			 precioCantidadxProducto=  mCarrito.sumatotaldeProductos(carrito);
			 precioIva=precioCantidadxProducto*(0.12);
			 precioTotal=precioCantidadxProducto+precioIva;
		 }else {
			 precioCantidadxProducto= 0;
		 }
	
	 }
		public void actionListenerActualizarProducto() {
			try {
				
				if(editarproducto.getCantidadIngresada()==0) {
					JSFUtil.crearMensajeERROR("Solo se ingresa cantidades mayores a 0");
				}else {
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
