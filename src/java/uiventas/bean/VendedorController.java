package uiventas.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import uiventas.bean.util.MobilePageController;
import ventas.entity.Vendedor;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import ventas.facade.VendedorFacade;

@Named(value = "vendedorController")
@ViewScoped
public class VendedorController extends AbstractController<Vendedor> implements Serializable{

    @Inject
    private MobilePageController mobilePageController;
    
    @EJB
    private VendedorFacade vendedorEJB;
    
    private Vendedor vendedor;
    
    private List<Vendedor> vendedores;
        

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    @PostConstruct
    public void init(){
        vendedores = vendedorEJB.findAll();
        vendedor = new Vendedor();
    }
    
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
