package distribuidorasanti.model.ventas.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import distribuidorasanti.model.core.entities.InvProducto;
import distribuidorasanti.model.core.managers.ManagerDAO;
import distribuidorasanti.model.inventario.managers.ManagerInventario;
import distribuidorasanti.model.ventas.dtos.ProductoDto;



/**
 * Session Bean implementation class ManagerVenCarrito
 */
@Stateless
@LocalBean
public class ManagerVenCarrito {
	@EJB
	ManagerInventario mInventario;
	@EJB
	ManagerDAO mDAO;
private int posicion;

	/**
	 * Default constructor.
	 */
	public ManagerVenCarrito() {
		// TODO Auto-generated constructor stub
	}

	public List<ProductoDto> generarDatosProductos() {
		List<ProductoDto> listado = new ArrayList<ProductoDto>();
		List<InvProducto> listadoInventario = mInventario.findAllProductos();
		InvProducto invproducto;
		for (int i = 0; i < listadoInventario.size(); i++) {
			invproducto = listadoInventario.get(i);
			listado.add(new ProductoDto(invproducto.getCodProducto(), invproducto.getProNombre(),
					invproducto.getInvMarca().getMarcNombre(), invproducto.getProPresio(), invproducto.getStock()));
		}
		return listado;

	}

	public ProductoDto findProductoDtobyCod(List<ProductoDto> listado, int codProducto) {
		for (int i = 0; i < listado.size(); i++) {
			if (codProducto == listado.get(i).getCodProducto()) {
				posicion=i;
				return listado.get(i);
			}
		}
		return null;
	}
	public int findPosicionProductoDtobyCod(List<ProductoDto> listado, int codProducto) {
		for (int i = 0; i < listado.size(); i++) {
			if (codProducto == listado.get(i).getCodProducto()) {
				return i;
			}
		}
		return -1;
	}
	public List<ProductoDto> findProductoDtobyNombre(List<ProductoDto> listado, String nombreProducto) {
		List<ProductoDto> listadoBusqueda = new ArrayList<ProductoDto>();
		for (int i = 0; i < listado.size(); i++) {
			if (nombreProducto.equals(listado.get(i).getProNombre())) {
				posicion=i;
				listadoBusqueda.add(listado.get(i));
			}
		}
		return listadoBusqueda;
	}
	
	public List<ProductoDto> findProductoDtobyMarca(List<ProductoDto> listado, String nombreMarca) {
		List<ProductoDto> listadoBusqueda = new ArrayList<ProductoDto>();
		for (int i = 0; i < listado.size(); i++) {
			if (nombreMarca.equals(listado.get(i).getMarcaNombre())) {
				posicion=i;
				listadoBusqueda.add(listado.get(i));
			}
		}
		return listadoBusqueda;
	}
	
	
    public void eliminarProducto(int codProducto,List<ProductoDto> lista,List<ProductoDto> carrito) throws Exception{
    int cantidad;
    int stock;
    ProductoDto producto=findProductoDtobyCod(carrito, codProducto);
    cantidad=producto.getCantidad();
    stock=producto.getStock();
    System.out.println( producto.getCodProducto());
    carrito.remove(posicion);
    producto=findProductoDtobyCod(lista, codProducto);
    producto.setCantidad(0);
    producto.setStock(stock+cantidad);
    }
    
    public void actualizarCantidad(ProductoDto productoEditado,List<ProductoDto> lista,List<ProductoDto> carrito) throws Exception{
        int cantidad;
       int cantidadSumatoria=0;
         int cantidadResultadoEdicion=0;
         int stockResultadoEdicion=0;
         int cantidadeditado=productoEditado.getCantidadIngresada();
         int posicionProductoEditado=findPosicionProductoDtobyCod(carrito, productoEditado.getCodProducto());
        cantidad=productoEditado.getCantidad();


        System.out.println( "Codigo Producto: "+productoEditado.getCodProducto());
        System.out.println( "Cantidad Producto: "+productoEditado.getCantidad());
        System.out.println( "Cantidad ProductoE: "+cantidadeditado);
    	cantidadSumatoria=(productoEditado.getCantidad()-cantidadeditado);
        System.out.println( "Cantidad ProductoS: "+cantidadSumatoria);
        if(cantidadSumatoria<0){
        	cantidadSumatoria=cantidadSumatoria;
            if(cantidadSumatoria+productoEditado.getStock()>=0) {
            	carrito.get(posicionProductoEditado).setCantidad(cantidadeditado);
            	carrito.get(posicionProductoEditado).setStock(cantidadSumatoria+productoEditado.getStock());
       
            }else {
            	throw new Exception("Error,No puedes comprar mas de lo que hay en stock.");
            }
        	
        	
        }else {
        	carrito.get(posicionProductoEditado).setCantidad(cantidadeditado);
        	carrito.get(posicionProductoEditado).setStock(cantidadSumatoria+(carrito.get(posicionProductoEditado).getStock()));

        }

        
        productoEditado.setCantidadIngresada(0);
        ProductoDto producto=findProductoDtobyCod(lista, productoEditado.getCodProducto());
        producto.setStock(carrito.get(posicionProductoEditado).getStock());
        producto.setCantidadIngresada(0);
        }
        
    
    
    
    
    
    
    
    
    
    
	public List<ProductoDto> agregarProductoCarrito(List<ProductoDto> carrito, ProductoDto producto) throws Exception {
		if (carrito == null){
			carrito = new ArrayList<ProductoDto>();
			if ((producto.getStock() - producto.getCantidadIngresada())>= 0) {
				producto.setStock(producto.getStock() - producto.getCantidadIngresada());
				producto.setCantidad(producto.getCantidadIngresada());
				carrito.add(producto);
				producto.setCantidadIngresada(0);
			} else {
				throw new Exception("Error,No puedes comprar mas de lo que hay en stock.");
			}
			return carrito;
		}
	
		if (findProductoDtobyCod(carrito, producto.getCodProducto()) != null) {
			throw new Exception("Error, Producto ya Ingresado");
		} else {
			if ((producto.getStock() - producto.getCantidadIngresada()) >= 0) {
				producto.setStock(producto.getStock() - producto.getCantidadIngresada());
				producto.setCantidad(producto.getCantidadIngresada());
				carrito.add(producto);
				producto.setCantidadIngresada(0);
			} else {
				throw new Exception("Error,No puedes comprar mas de lo que hay en stock.");
			}
		}

		return carrito;

	}

}
