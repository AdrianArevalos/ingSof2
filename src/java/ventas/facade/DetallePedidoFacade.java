
package ventas.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ventas.entity.DetallePedido;
import ventas.entity.Pedido;

@Stateless
public class DetallePedidoFacade extends AbstractFacade<DetallePedido> {

    @PersistenceContext(unitName = "VentasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallePedidoFacade() {
        super(DetallePedido.class);
    }
    
    public void registrarDetallePedido(List<DetallePedido> pedidos){
        String consulta;
        int id_pedido = 0;
        try {
            consulta = "SELECT MAX(idPedido) FROM pedido";
            Query query = em.createQuery(consulta);
            
            List<Pedido> lista = query.getResultList();
            if(!lista.isEmpty()){
                id_pedido = lista.get(0).getIdPedido();
            }
            
            for(DetallePedido det: pedidos){
                consulta = "INSERT INTO detallePedido (idPedido, idProduto, cantidad) values(?,?,?)";
                query = em.createQuery(consulta);
                query.setParameter(1, id_pedido);
                query.setParameter(2, det.getProducto().getIdProducto());
                query.setParameter(3, det.getCantidad());
                
                query.executeUpdate();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
}
