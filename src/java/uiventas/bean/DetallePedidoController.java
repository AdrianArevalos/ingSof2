package uiventas.bean;

import java.util.ArrayList;
import java.util.List;
import uiventas.bean.util.MobilePageController;
import ventas.entity.DetallePedido;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "detallePedidoController")
@ViewScoped
public class DetallePedidoController extends AbstractController<DetallePedido> {

    @Inject
    private PedidoController pedidoController;
    @Inject
    private ProductoController productoController;
    @Inject
    private MobilePageController mobilePageController;

    
    public DetallePedidoController() {
        // Inform the Abstract parent controller of the concrete DetallePedido Entity
        super(DetallePedido.class);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getDetallePedidoPK().setIdPedido(this.getSelected().getPedido().getIdPedido());
        this.getSelected().getDetallePedidoPK().setIdProducto(this.getSelected().getProducto().getIdProducto());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setDetallePedidoPK(new ventas.entity.DetallePedidoPK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        pedidoController.setSelected(null);
        productoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Pedido controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePedido(ActionEvent event) {
        if (this.getSelected() != null && pedidoController.getSelected() == null) {
            pedidoController.setSelected(this.getSelected().getPedido());
        }
    }

    /**
     * Sets the "selected" attribute of the Producto controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareProducto(ActionEvent event) {
        if (this.getSelected() != null && productoController.getSelected() == null) {
            productoController.setSelected(this.getSelected().getProducto());
        }
    }
}
