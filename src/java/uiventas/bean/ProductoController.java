package uiventas.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import uiventas.bean.util.MobilePageController;
import ventas.entity.Producto;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "productoController")
@ViewScoped
public class ProductoController extends AbstractController<Producto>{

    @Inject
    private MobilePageController mobilePageController;
    
    public ProductoController() {
        // Inform the Abstract parent controller of the concrete Producto Entity
        super(Producto.class);
    }

    public String navigateDetallePedidoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetallePedido_items", this.getSelected().getDetallePedidoCollection());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/detallePedido/index";
    }

    
}
