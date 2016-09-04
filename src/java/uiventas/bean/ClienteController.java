package uiventas.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import uiventas.bean.util.MobilePageController;
import ventas.entity.Cliente;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import ventas.facade.ClienteFacade;

@Named(value = "clienteController")
@RequestScoped
public class ClienteController extends AbstractController<Cliente> implements Serializable{
  
    @Inject
    private MobilePageController mobilePageController;
    @EJB
    private ClienteFacade clienteEJB;
    private Cliente cliente;
    private int cedulaRuc;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getCedulaRuc() {
        return cedulaRuc;
    }

    public void setCedulaRuc(int cedulaRuc) {
        this.cedulaRuc = cedulaRuc;
    }
    
    @PostConstruct
    public void init(){
        this.cliente = clienteEJB.find(this.cedulaRuc);
    }
    
    public ClienteController() {
        // Inform the Abstract parent controller of the concrete Cliente Entity
        super(Cliente.class);
    }

    public String navigatePedidoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Pedido_items", this.getSelected().getPedidoCollection());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/pedido/index";
    }
    
    public void buscarCliente(){
        init();
        //this.cliente = clienteEJB.find(this.cedulaRuc);
        System.out.println(cliente.getApellido());
    }
}
