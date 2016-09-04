/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ventas.entity.DetallePedido;

/**
 *
 * @author mundo
 */
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
    
}
