package distribuidorasanti.model.ventas.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import distribuidorasanti.model.core.entities.VenCliente;
import distribuidorasanti.model.core.entities.VenVenta;
import distribuidorasanti.model.core.managers.ManagerDAO;



/**
 * Session Bean implementation class ManagerVenVenta
 */
@Stateless
@LocalBean
public class ManagerVenVenta {

    /**
     * Default constructor. 
     */
	@EJB
    ManagerDAO mDAO;
	
    public ManagerVenVenta() {
        // TODO Auto-generated constructor stub
    }

    
    public List<VenVenta> findAllVenta(){
    	return mDAO.findAll(VenVenta.class, "idVenta");
    }
    public int  findUltimaVenta(){
    	List<VenVenta> listaVenta=findAllVenta();
    	int posicion=listaVenta.size();
    	return posicion;
    }
    
    public void insertarCabeceraVenta(VenVenta nuevaVenta) throws Exception {
    	
    
    	mDAO.insertar(nuevaVenta);
    }
    
    public void eliminarCabeceraVenta(int idVenta) throws Exception{
    	VenVenta cabeceraVenta = (VenVenta)mDAO.findById(VenVenta.class, idVenta);
    	mDAO.eliminar(VenVenta.class, cabeceraVenta.getIdVenta());
    }
    
    public VenVenta findCabeceraVentaById(int idVenta) throws Exception{
    	VenVenta busqVenta = (VenVenta)mDAO.findById(VenVenta.class, idVenta);
    	return busqVenta;
    }
}
