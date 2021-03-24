package distribuidorasanti.model.inventario.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import distribuidorasanti.model.core.entities.InvMarca;
import distribuidorasanti.model.core.managers.ManagerDAO;



/**
 * Session Bean implementation class ManagerDistribuidores
 */
@Stateless
@LocalBean
public class ManagerMarca {
@EJB
ManagerDAO mDAO;
    public ManagerMarca() {
        
    }
    
    //Tabla Marca
    public List<InvMarca> findAllMarca(){
 	   return mDAO.findAll(InvMarca.class, "idMarca");
    }
    
    public void insertarMarca(InvMarca nuevaMarca) throws Exception{
 	   mDAO.insertar(nuevaMarca);
    }
    
    public void actualizarMarca(InvMarca edicionMarca) throws Exception{
 	   InvMarca marca = (InvMarca) mDAO.findById(InvMarca.class, edicionMarca.getIdMarca());
 	   marca.setMarcNombre(edicionMarca.getMarcNombre());
 	   mDAO.actualizar(marca);
    }
    
    public void eliminarMarca(int idMarca) throws Exception{
 	   mDAO.eliminar(InvMarca.class, idMarca);
    }

    
    
  
}
