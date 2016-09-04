package uiventas.bean;

import uiventas.bean.util.MobilePageController;
import ventas.entity.Pedido;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "pedidoController")
@ViewScoped
public class PedidoController extends AbstractController<Pedido> {

    @Inject
    private ClienteController cedulaRucClienteController;
    @Inject
    private FacturaController nroFacturaController;
    @Inject
    private PresupuestoController idPresupuestoController;
    @Inject
    private VendedorController cedulaVendedorController;
    @Inject
    private MobilePageController mobilePageController;

    public PedidoController() {
        // Inform the Abstract parent controller of the concrete Pedido Entity
        super(Pedido.class);
    }

    public void resetParents() {
        cedulaRucClienteController.setSelected(null);
        nroFacturaController.setSelected(null);
        idPresupuestoController.setSelected(null);
        cedulaVendedorController.setSelected(null);
    }


    public String navigateDetallePedidoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetallePedido_items", this.getSelected().getDetallePedidoCollection());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/detallePedido/index";
    }


    public String navigatePresupuestoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Presupuesto_items", this.getSelected().getPresupuestoCollection());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/presupuesto/index";
    }

    public String navigateFacturaCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Factura_items", this.getSelected().getFacturaCollection());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/factura/index";
    }

    public void prepareCedulaRucCliente(ActionEvent event) {
        if (this.getSelected() != null && cedulaRucClienteController.getSelected() == null) {
            cedulaRucClienteController.setSelected(this.getSelected().getCedulaRucCliente());
        }
    }


    public void prepareNroFactura(ActionEvent event) {
        if (this.getSelected() != null && nroFacturaController.getSelected() == null) {
            nroFacturaController.setSelected(this.getSelected().getNroFactura());
        }
    }

    public void prepareIdPresupuesto(ActionEvent event) {
        if (this.getSelected() != null && idPresupuestoController.getSelected() == null) {
            idPresupuestoController.setSelected(this.getSelected().getIdPresupuesto());
        }
    }

    public void prepareCedulaVendedor(ActionEvent event) {
        if (this.getSelected() != null && cedulaVendedorController.getSelected() == null) {
            cedulaVendedorController.setSelected(this.getSelected().getCedulaVendedor());
        }
    }
    
}
