package cz.cvut.fel.x36tjv.managedbeans.client;

import cz.cvut.fel.x36tjv.ejbs.ClientSessionBean;
import cz.cvut.fel.x36tjv.entities.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.validation.constraints.NotNull;

@ManagedBean(name = "chooseCustomer")
@RequestScoped
public class ChooseCustomer {

    @EJB
    private ClientSessionBean bean;

    @NotNull(message = "Please, choose a customer.")
    private Long customerID;

    public ChooseCustomer() {
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

    public String choose() {
        bean.chooseCustomer(customerID);
        return "accounts";
    }

    public List<SelectItem> getCustomers() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (Customer c : bean.getCustomers()) {
            list.add(new SelectItem(c.getId(), String.format("%s %s", c.getFirstName(), c.getLastName())));
        }
        return list;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }
}
