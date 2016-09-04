/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mundo
 */
@Entity
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pedido")
    private Integer idPedido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Collection<DetallePedido> detallePedidoCollection;
    @OneToMany(mappedBy = "idPedido")
    private Collection<Presupuesto> presupuestoCollection;
    @OneToMany(mappedBy = "idPedido")
    private Collection<Factura> facturaCollection;
    @JoinColumn(name = "cedula_ruc_cliente", referencedColumnName = "cedula_ruc")
    @ManyToOne
    private Cliente cedulaRucCliente;
    @JoinColumn(name = "nro_factura", referencedColumnName = "nro_factura")
    @ManyToOne
    private Factura nroFactura;
    @JoinColumn(name = "id_presupuesto", referencedColumnName = "id_presupuesto")
    @ManyToOne
    private Presupuesto idPresupuesto;
    @JoinColumn(name = "cedula_vendedor", referencedColumnName = "cedula")
    @ManyToOne
    private Vendedor cedulaVendedor;

    public Pedido() {
    }

    public Pedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    @XmlTransient
    public Collection<DetallePedido> getDetallePedidoCollection() {
        return detallePedidoCollection;
    }

    public void setDetallePedidoCollection(Collection<DetallePedido> detallePedidoCollection) {
        this.detallePedidoCollection = detallePedidoCollection;
    }

    @XmlTransient
    public Collection<Presupuesto> getPresupuestoCollection() {
        return presupuestoCollection;
    }

    public void setPresupuestoCollection(Collection<Presupuesto> presupuestoCollection) {
        this.presupuestoCollection = presupuestoCollection;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }

    public Cliente getCedulaRucCliente() {
        return cedulaRucCliente;
    }

    public void setCedulaRucCliente(Cliente cedulaRucCliente) {
        this.cedulaRucCliente = cedulaRucCliente;
    }

    public Factura getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(Factura nroFactura) {
        this.nroFactura = nroFactura;
    }

    public Presupuesto getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(Presupuesto idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public Vendedor getCedulaVendedor() {
        return cedulaVendedor;
    }

    public void setCedulaVendedor(Vendedor cedulaVendedor) {
        this.cedulaVendedor = cedulaVendedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ventas.entity.Pedido[ idPedido=" + idPedido + " ]";
    }
    
}
