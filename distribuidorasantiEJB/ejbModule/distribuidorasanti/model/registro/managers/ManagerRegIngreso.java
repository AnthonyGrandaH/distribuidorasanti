package distribuidorasanti.model.registro.managers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;

import distribuidorasanti.model.core.entities.InvDistribuidore;
import distribuidorasanti.model.core.entities.InvMarca;
import distribuidorasanti.model.core.entities.InvProducto;
import distribuidorasanti.model.core.entities.RegIngreso;
import distribuidorasanti.model.core.entities.VenVenta;
import distribuidorasanti.model.core.managers.ManagerDAO;
import distribuidorasanti.model.registros.dto.RegistroIngresosDTO;

/**
 * Session Bean implementation class ManagerRegIngreso
 */
@Stateless
@LocalBean
public class ManagerRegIngreso {
	@EJB
	ManagerDAO mDAO;
	double PrecioTotal;
    /**
     * Default constructor. 
     */
    public ManagerRegIngreso() {
        // TODO Auto-generated constructor stub
    }

    
    
    public List<RegIngreso> findAllIngresos(){
    	return mDAO.findAll(InvProducto.class, "idIngresos");
    }
    public void insertarIngreso(RegIngreso nuevoIngreso) throws Exception{
    	
    	mDAO.insertar(nuevoIngreso);
    }
 
   public List<RegistroIngresosDTO> findAllRegistroIngresoDTO(){
	   List<RegistroIngresosDTO> listaIngresosDTO=new ArrayList<RegistroIngresosDTO>();
	   RegistroIngresosDTO registroIngresoDTO;
	   List<VenVenta> listaVentas= mDAO.findAll(VenVenta.class);
	   for(int i=0;i<listaVentas.size();i++) {

		   registroIngresoDTO=new RegistroIngresosDTO(listaVentas.get(i).getIdVenta(), listaVentas.get(i).getVenTotal()
				   , listaVentas.get(i).getVenCliente().getCliCedula(), listaVentas.get(i).getFechaVen());
		   listaIngresosDTO.add(registroIngresoDTO);
	   }
	   return listaIngresosDTO;
   }

    
   public List<RegistroIngresosDTO> findAllRegistroByFecha(List<RegistroIngresosDTO> listaIngresos,int mes, int anio){
	   List<RegistroIngresosDTO> listanuevoIngresosDTO=new ArrayList<RegistroIngresosDTO>();
	   RegistroIngresosDTO registroIngresoDTO;
	   double precioTotalVentas=0;
	   int mesRegistro;
	   int anioRegistro;
	   for(int i=0;i<listaIngresos.size();i++) {
		   registroIngresoDTO=listaIngresos.get(i);
		   mesRegistro=registroIngresoDTO.getFechaVen().getMonth() +1;
		   System.out.println("Mes manager: " +mesRegistro);
		   anioRegistro=registroIngresoDTO.getFechaVen().getYear()+1900;
		   System.out.println("Año manager: " +anioRegistro);
		   if(mesRegistro==mes && anioRegistro==anio) {
			   listanuevoIngresosDTO.add(registroIngresoDTO);
			   precioTotalVentas= precioTotalVentas+registroIngresoDTO.getVenTotal();
		   }
	   }
	   PrecioTotal=precioTotalVentas;
	   return listanuevoIngresosDTO;
	   
   }



public double getPrecioTotal() {
	return PrecioTotal;
}



public void setPrecioTotal(double precioTotal) {
	PrecioTotal = precioTotal;
}
   
}
