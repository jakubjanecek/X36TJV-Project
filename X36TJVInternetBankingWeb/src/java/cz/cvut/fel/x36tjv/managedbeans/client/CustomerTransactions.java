package cz.cvut.fel.x36tjv.managedbeans.client;

import cz.cvut.fel.x36tjv.ejbs.ClientSessionBean;
import cz.cvut.fel.x36tjv.entities.BankTransaction;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "clientCustomerTransactions")
@RequestScoped
public class CustomerTransactions {

    @EJB
    private ClientSessionBean bean;

    public CustomerTransactions() {
    }

    @PostConstruct
    private final void init() {
        Map<String, Object> attrs = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (attrs.containsKey("ClientSessionBean")) {
            bean = (ClientSessionBean) attrs.get("ClientSessionBean");
            if (bean == null) {
                attrs.put("ClientSessionBean", bean);
            }
        }
        else {
            attrs.put("ClientSessionBean", bean);
        }
    }

    public List<BankTransaction> getTransactions() {
        if (bean == null) {
            throw new RuntimeException("FUCK1");
        }
        return bean.getTransactions();
    }
}
