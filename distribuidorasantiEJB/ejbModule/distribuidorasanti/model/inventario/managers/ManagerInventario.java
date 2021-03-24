package distribuidorasanti.model.inventario.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import distribuidorasanti.model.core.entities.InvDistribuidore;
import distribuidorasanti.model.core.entities.InvMarca;
import distribuidorasanti.model.core.entities.InvProducto;
import distribuidorasanti.model.core.managers.ManagerDAO;





/**
 * Session Bean implementation class ManagerDistribuidores
 */
@Stateless
@LocalBean
public class ManagerInventario {
@EJB
ManagerDAO mDAO;
    public ManagerInventario() {
        
    }
 
    public List<InvProducto> findAllProductos(){
    	return mDAO.findAll(InvProducto.class, "proNombre");
    }
    public void insertarProducto(InvProducto nuevoProducto, int idDistribuidor, int idMarca) throws Exception{
    	InvDistribuidore distribuidor= (InvDistribuidore)mDAO.findById(InvDistribuidore.class, idDistribuidor);
    	InvMarca marca =(InvMarca)mDAO.findById(InvMarca.class, idMarca);
    	nuevoProducto.setInvMarca(marca);
    	nuevoProducto.setInvDistribuidore(distribuidor);
    	mDAO.insertar(nuevoProducto);
    }
    public void actualizarProducto(InvProducto edicionProducto, int idDistribuidor, int idMarca)throws Exception {
    	InvDistribuidore distribuidor= (InvDistribuidore)mDAO.findById(InvDistribuidore.class, idDistribuidor);
    	InvMarca marca =(InvMarca)mDAO.findById(InvMarca.class, idMarca);
    	InvProducto producto=(InvProducto)mDAO.findById(InvProducto.class, edicionProducto.getCodProducto());
    	producto.setInvDistribuidore(distribuidor);
    	producto.setInvMarca(marca);
    	producto.setProNombre(edicionProducto.getProNombre());
    	producto.setProPresioAd(edicionProducto.getProPresioAd());
    	producto.setProPresio(edicionProducto.getProPresio());
    	producto.setProFechaCompra(edicionProducto.getProFechaCompra());
    	producto.setStock(edicionProducto.getStock());
    	mDAO.actualizar(producto);
    }
 
    public void eliminarProducto(int idProducto) throws Exception {
    	InvProducto producto=(InvProducto)mDAO.findById(InvProducto.class,idProducto);
    	mDAO.eliminar(InvProducto.class,producto.getCodProducto());
    }
    

    
    
  
}
