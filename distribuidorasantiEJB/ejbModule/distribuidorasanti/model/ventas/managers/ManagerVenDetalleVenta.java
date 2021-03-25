package distribuidorasanti.model.ventas.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import distribuidorasanti.model.core.entities.InvProducto;
import distribuidorasanti.model.core.entities.SegAsignacion;
import distribuidorasanti.model.core.entities.VenCliente;
import distribuidorasanti.model.core.entities.VenDetalleVenta;
import distribuidorasanti.model.core.entities.VenVenta;
import distribuidorasanti.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerDetalleVenta
 */
@Stateless
@LocalBean
public class ManagerVenDetalleVenta {

	@EJB
	ManagerDAO mDAO;

	public ManagerVenDetalleVenta() {
		// TODO Auto-generated constructor stub
	}

	public List<VenDetalleVenta> findAllDetalleVentas() {
		return mDAO.findAll(VenDetalleVenta.class, "idDtVenta");
	}

	public void insertarVentaDetalle(VenDetalleVenta nuevodetalleVenta, int idVenta, int codProducto) throws Exception {

		VenVenta venta = (VenVenta) mDAO.findById(VenVenta.class, idVenta);
		InvProducto producto = (InvProducto) mDAO.findById(InvProducto.class, codProducto);
		nuevodetalleVenta.setInvProducto(producto);
		nuevodetalleVenta.setVenVenta(venta);

		mDAO.insertar(nuevodetalleVenta);
	}

	public List<VenDetalleVenta> findDetalleVentaByIDVenta(int idVenta){
    	String consulta="o.VenVenta.idVenta="+idVenta;
		List<VenDetalleVenta> listaDetalleVentas=mDAO.findWhere(VenDetalleVenta.class, consulta, null);
		return listaDetalleVentas;
    }
	  
	    
	public void eliminarVentaDetalle(int IdDtVenta) throws Exception {

		VenDetalleVenta detalleVenta = (VenDetalleVenta) mDAO.findById(VenDetalleVenta.class, IdDtVenta);
		mDAO.eliminar(VenDetalleVenta.class, detalleVenta.getIdDtVenta());
	}

}
