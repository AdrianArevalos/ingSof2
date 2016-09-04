package ventas.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ventas.entity.Pedido;


@Stateless
public class PedidoFacade extends AbstractFacade<Pedido> {

    @PersistenceContext(unitName = "VentasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoFacade() {
        super(Pedido.class);
    }
    
    public void registrarPedido(int cedulaCliente, int cedulaVendedor){
        String consulta;
        try {
            System.out.println(cedulaCliente + " " + cedulaVendedor);
            consulta = "INSERT INTO Pedido (cedulaRucCliente, cedulaVendedor) values(?,?)";
            Query query = em.createQuery(consulta);
            
            query.setParameter(1, cedulaCliente);
            query.setParameter(2, cedulaVendedor);
            query.executeUpdate();
            
        } catch (Exception e) {
            throw e;
        }
    }
}
