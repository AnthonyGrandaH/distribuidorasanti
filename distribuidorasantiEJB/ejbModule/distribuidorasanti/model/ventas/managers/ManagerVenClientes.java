package distribuidorasanti.model.ventas.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import distribuidorasanti.model.core.entities.VenCliente;
import distribuidorasanti.model.core.managers.ManagerDAO;



/**
 * Session Bean implementation class ManagerVenClientes
 */
@Stateless
@LocalBean
public class ManagerVenClientes {
	@EJB
	ManagerDAO mDAO;
    /**
     * Default constructor. 
     */
    public ManagerVenClientes() {
       
    }

  //Tabla Distribuidores
    public List<VenCliente> findAllVenClientes(){
    	return mDAO.findAll(VenCliente.class, "cliNombre");
    }
    
    public void insertarClientes(VenCliente nuevoCliente) throws Exception {
    	mDAO.insertar(nuevoCliente);
    }

    public void actualizarClientes(VenCliente edicionCliente) throws Exception{
    	VenCliente cli = (VenCliente) mDAO.findById(VenCliente.class, edicionCliente.getCliCedula());
    	cli.setCliNombre(edicionCliente.getCliNombre());
    	cli.setCliApellido(edicionCliente.getCliApellido());
    	cli.setCliDireccion(edicionCliente.getCliDireccion());
    	cli.setCliCedula(edicionCliente.getCliCedula());
    	cli.setCliTelefono(edicionCliente.getCliTelefono());
    	mDAO.actualizar(cli);
    }
    
    public void eliminaCliente(String cedula) throws Exception{
    	mDAO.eliminar(VenCliente.class, cedula);
    }
    
    public VenCliente buscarClientebyCI(String cedula) throws Exception{
    	VenCliente busqCli = (VenCliente) mDAO.findById(VenCliente.class, cedula);
    	if(busqCli==null) {
    	 	throw new Exception("Error,Cliente no encontrado");
    	}
    	return busqCli;
    }
    
    public VenCliente buscarClientebyID(int idCliente) throws Exception{
    	VenCliente busqCli = (VenCliente) mDAO.findById(VenCliente.class, idCliente);
    	if(busqCli==null) {
    	 	throw new Exception("Error,Cliente no encontrado");
    	}
    	return busqCli;
    }
     
}
