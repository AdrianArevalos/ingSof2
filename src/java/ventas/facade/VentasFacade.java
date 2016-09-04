
package ventas.facade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ventas.entity.DetallePedido;

@Stateless
public class VentasFacade implements Serializable{
    
    @PersistenceContext(unitName = "VentasPU")
    private EntityManager em;
    
    @EJB
    private PedidoFacade pedidoEJB;
    @EJB
    private DetallePedidoFacade detallePedidoEJB;
 
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentasFacade() {
        pedidoEJB = new PedidoFacade();
        detallePedidoEJB = new DetallePedidoFacade();
    }
    
    public void registrar(int cedulaCliente, int cedulaVendedor, List<DetallePedido> pedidos)throws Exception{
        try {
            pedidoEJB.registrarPedido(cedulaCliente, cedulaVendedor);
            detallePedidoEJB.registrarDetallePedido(pedidos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Insercion Exitosa"));
        } catch (Exception e) {
            throw e;
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aviso","Error"));
        }
    }   
}
