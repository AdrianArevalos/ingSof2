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
import ventas.entity.DetallePedido;
import ventas.facade.ProductoFacade;

@Named(value = "productoController")
@ViewScoped
public class ProductoController extends AbstractController<Producto> implements Serializable{

    @Inject
    private MobilePageController mobilePageController;

    @EJB
    private ProductoFacade productoEJB;
    private Producto producto;
    private List<Producto> productos;
    private int cantidad;
    
    
    private List<DetallePedido> lista = new ArrayList();

    public List<DetallePedido> getLista() {
        return lista;
    }

    public void setLista(List<DetallePedido> lista) {
        this.lista = lista;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    @PostConstruct
    public void init(){
        productos = productoEJB.findAll();
        producto = new Producto();
    }
    
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

    public void agregarProducto(){
        DetallePedido det = new DetallePedido();
        det.setCantidad(cantidad);
        det.setProducto(producto);
        this.lista.add(det); 
    }
    
}
