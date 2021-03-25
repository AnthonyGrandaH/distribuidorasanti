package distribuidorasanti.model.registro.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import distribuidorasanti.model.core.entities.InvProducto;
import distribuidorasanti.model.core.entities.RegEgreso;
import distribuidorasanti.model.core.entities.RegIngreso;
import distribuidorasanti.model.core.entities.VenVenta;
import distribuidorasanti.model.core.managers.ManagerDAO;
import distribuidorasanti.model.registros.dto.RegistroEgresosDTO;
import distribuidorasanti.model.registros.dto.RegistroIngresosDTO;

/**
 * Session Bean implementation class ManagerEgresos
 */
@Stateless
@LocalBean
public class ManagerEgresos {

    /**
     * Default constructor. 
     */
	@EJB
	ManagerDAO mDAO;
	double PrecioTotal;
    public ManagerEgresos() {
        // TODO Auto-generated constructor stub
    }

    public List<RegEgreso> findAllEgresos(){
    	return mDAO.findAll(RegEgreso.class);
    }
    public void insertarEgresos(RegEgreso nuevoEgreso) throws Exception{
    	
    	mDAO.insertar(nuevoEgreso);
    }
 
   public List<RegistroEgresosDTO> findAllRegistroEgresosDTO(){
	   List<RegistroEgresosDTO> listaEgresosDTO=new ArrayList<RegistroEgresosDTO>();
	   RegistroEgresosDTO registroEgresosDTO;
	   List<InvProducto> listaProductos= mDAO.findAll(InvProducto.class);
	   for(int i=0;i<listaProductos.size();i++) {

		   registroEgresosDTO=new RegistroEgresosDTO(listaProductos.get(i).getCodProducto(), listaProductos.get(i).getProNombre(), 
				   listaProductos.get(i).getProPresioAd(), listaProductos.get(i).getProFechaCompra());
		   listaEgresosDTO.add(registroEgresosDTO);
	   }
	   return listaEgresosDTO;
   }

    
   public List<RegistroEgresosDTO> findAllRegistroByFecha(List<RegistroEgresosDTO> listaEgresos,int mes, int anio){
	   List<RegistroEgresosDTO> listanuevoEgresosDTO=new ArrayList<RegistroEgresosDTO>();
	   RegistroEgresosDTO registroEgresosDTO;
	   double precioTotalVentas=0;
	   int mesRegistro;
	   int anioRegistro;
	   for(int i=0;i<listaEgresos.size();i++) {
		   registroEgresosDTO=listaEgresos.get(i);
		   mesRegistro=registroEgresosDTO.getProFechaCompra().getMonth() +1;
		   System.out.println("Mes manager: " +mesRegistro);
		   anioRegistro=registroEgresosDTO.getProFechaCompra().getYear()+1900;
		   System.out.println("Año manager: " +anioRegistro);
		   if(mesRegistro==mes && anioRegistro==anio) {
			   listanuevoEgresosDTO.add(registroEgresosDTO);
			   precioTotalVentas= precioTotalVentas+registroEgresosDTO.getProPrecioAd();
		   }
	   }
	   PrecioTotal=precioTotalVentas;
	   return listanuevoEgresosDTO;
	   
   }



public double getPrecioTotal() {
	return PrecioTotal;
}



public void setPrecioTotal(double precioTotal) {
	PrecioTotal = precioTotal;
}
}
