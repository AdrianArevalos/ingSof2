
package uiventas.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import ventas.entity.Cliente;
import ventas.entity.DetallePedido;
import ventas.entity.Producto;
import ventas.entity.Vendedor;
import ventas.facade.ClienteFacade;
import ventas.facade.ProductoFacade;
import ventas.facade.VendedorFacade;
import ventas.facade.VentasFacade;

@Named(value = "ventasController")
@ViewScoped
public class VentasController implements Serializable {
    
    @EJB
    private ClienteFacade clienteEJB;
    @EJB
    private VendedorFacade vendedorEJB;
    @EJB
    private ProductoFacade productoEJB;
    
    private VentasFacade ventasEJB = new VentasFacade();
    
    private Cliente cliente;
    private Vendedor vendedor;
    private Producto producto;
    private int cantidad;
    private int cedulaRuc;
    private List<Producto> productos;
    private List<Vendedor> vendedores;
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
        vendedores = vendedorEJB.findAll();
        vendedor = new Vendedor();
        productos = productoEJB.findAll();
        producto = new Producto();
        
    }
    
    public void buscarCliente(){
        this.cliente = clienteEJB.find(this.cedulaRuc);
        System.out.println(cliente.getApellido());
    }
    
    public void agregarProducto(){
        DetallePedido det = new DetallePedido();
        det.setCantidad(cantidad);
        det.setProducto(producto);
        det.setImporte(producto.getPrecioUnitario()*cantidad);
        this.lista.add(det); 
    }
    
    public void realizarVenta() throws Exception{
        try {
            ventasEJB.registrar(cliente.getCedulaRuc(), vendedor.getCedula(), lista);
        } catch (Exception e) {
            throw e;
        }
    }
    
    
}
