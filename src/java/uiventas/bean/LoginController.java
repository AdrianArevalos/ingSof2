
package uiventas.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import ventas.entity.Usuario;
import ventas.facade.UsuarioFacade;

@Named
@ViewScoped
public class LoginController implements Serializable{
    
    @EJB
    private UsuarioFacade EJBusuario;
    private Usuario usuario;
    
    @PostConstruct    
    public void init(){
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String iniciarSesion(){
        Usuario us;
        String redireccion = null;
        
        try {
            us = EJBusuario.iniciarSesion(usuario);
            if(us != null){
                redireccion = "/index?faces-redirect=true";
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aviso","Credenciales Incorrentos"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error Fatal"));
        }
        return redireccion;    
    }
}
