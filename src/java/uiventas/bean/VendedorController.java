package uiventas.bean;

import uiventas.bean.util.MobilePageController;
import ventas.entity.Vendedor;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "vendedorController")
@ViewScoped
public class VendedorController extends AbstractController<Vendedor> {

    @Inject
    private MobilePageController mobilePageController;
    
    public VendedorController() {
        // Inform the Abstract parent controller of the concrete Vendedor Entity
        super(Vendedor.class);
    }

    public String navigatePedidoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Pedido_items", this.getSelected().getPedidoCollection());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/pedido/index";
    }

}
