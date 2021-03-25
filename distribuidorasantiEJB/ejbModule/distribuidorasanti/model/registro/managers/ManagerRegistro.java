package distribuidorasanti.model.registro.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import distribuidorasanti.model.core.entities.RegRegistro;
import distribuidorasanti.model.core.entities.VenCliente;
import distribuidorasanti.model.core.entities.VenDetalleVenta;
import distribuidorasanti.model.core.entities.VenVenta;
import distribuidorasanti.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerRegistro
 */
@Stateless
@LocalBean
public class ManagerRegistro {

    /**
     * Default constructor. 
     */
	@EJB
	ManagerDAO mDAO;
    public ManagerRegistro() {
        // TODO Auto-generated constructor stub
    }
    
    public List<RegRegistro> findAllRegistro(){
    	return mDAO.findAll(RegRegistro.class,"idContabilidad");
    }
}
