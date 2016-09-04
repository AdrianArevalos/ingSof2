package uiventas.bean;

import uiventas.bean.util.MobilePageController;
import ventas.entity.Presupuesto;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "presupuestoController")
@ViewScoped
public class PresupuestoController extends AbstractController<Presupuesto> {

    @Inject
    private PedidoController idPedidoController;
    @Inject
    private MobilePageController mobilePageController;

    public PresupuestoController() {
        // Inform the Abstract parent controller of the concrete Presupuesto Entity
        super(Presupuesto.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idPedidoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Pedido controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdPedido(ActionEvent event) {
        if (this.getSelected() != null && idPedidoController.getSelected() == null) {
            idPedidoController.setSelected(this.getSelected().getIdPedido());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Pedido entities that are
     * retrieved from Presupuesto?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Pedido page
     */
    public String navigatePedidoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Pedido_items", this.getSelected().getPedidoCollection());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/pedido/index";
    }

}
