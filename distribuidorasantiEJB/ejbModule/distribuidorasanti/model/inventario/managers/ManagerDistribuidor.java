package distribuidorasanti.model.inventario.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import distribuidorasanti.model.core.entities.InvDistribuidore;
import distribuidorasanti.model.core.managers.ManagerDAO;



/**
 * Session Bean implementation class ManagerDistribuidores
 */
@Stateless
@LocalBean
public class ManagerDistribuidor {
@EJB
ManagerDAO mDAO;
    public ManagerDistribuidor() {
        
    }
    
  //Tabla Distribuidores
    public List<InvDistribuidore> findAllDistribuidores(){
    	return mDAO.findAll(InvDistribuidore.class, "nombreDist");
    }
    
    public void insertarDistribuidor(InvDistribuidore nuevoDistribuidor) throws Exception {
    	mDAO.insertar(nuevoDistribuidor);
    }

    public void actualizarDistribuidor(InvDistribuidore edicionDistribuidor) throws Exception{
    	InvDistribuidore dist = (InvDistribuidore) mDAO.findById(InvDistribuidore.class, edicionDistribuidor.getIdDistribuidor());
    	dist.setNombreDist(edicionDistribuidor.getNombreDist());
    	dist.setTelefonoDist(edicionDistribuidor.getTelefonoDist());
    	dist.setDireccionDist(edicionDistribuidor.getDireccionDist());
    	mDAO.actualizar(dist);
    }
    
    public void eliminarDistribuidor(int idDistribuidor) throws Exception{
    	mDAO.eliminar(InvDistribuidore.class, idDistribuidor);
    }
    
    
  
}
