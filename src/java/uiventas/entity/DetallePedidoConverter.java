package uiventas.entity;

import ventas.entity.DetallePedido;
import ventas.facade.DetallePedidoFacade;
import uiventas.bean.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@FacesConverter(value = "detallePedidoConverter")
public class DetallePedidoConverter implements Converter {

    @Inject
    private DetallePedidoFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ventas.entity.DetallePedidoPK getKey(String value) {
        ventas.entity.DetallePedidoPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ventas.entity.DetallePedidoPK();
        key.setIdPedido(Integer.parseInt(values[0]));
        key.setIdProducto(Integer.parseInt(values[1]));
        return key;
    }

    String getStringKey(ventas.entity.DetallePedidoPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getIdPedido());
        sb.append(SEPARATOR);
        sb.append(value.getIdProducto());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof DetallePedido) {
            DetallePedido o = (DetallePedido) object;
            return getStringKey(o.getDetallePedidoPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DetallePedido.class.getName()});
            return null;
        }
    }

}
