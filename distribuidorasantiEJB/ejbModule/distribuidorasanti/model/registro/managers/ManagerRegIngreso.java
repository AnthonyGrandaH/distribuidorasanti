package distribuidorasanti.model.registro.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;

import distribuidorasanti.model.core.entities.InvDistribuidore;
import distribuidorasanti.model.core.entities.InvMarca;
import distribuidorasanti.model.core.entities.InvProducto;
import distribuidorasanti.model.core.entities.RegIngreso;
import distribuidorasanti.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerRegIngreso
 */
@Stateless
@LocalBean
public class ManagerRegIngreso {
	@EJB
	ManagerDAO mDAO;
    /**
     * Default constructor. 
     */
    public ManagerRegIngreso() {
        // TODO Auto-generated constructor stub
    }

    
    
    public List<RegIngreso> findAllIngresos(){
    	return mDAO.findAll(InvProducto.class, "idIngresos");
    }
    public void insertarProducto(RegIngreso nuevoIngreso) throws Exception{
    	
    	mDAO.insertar(nuevoIngreso);
    }
 

    
}
