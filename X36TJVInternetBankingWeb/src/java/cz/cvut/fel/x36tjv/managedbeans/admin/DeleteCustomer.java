package cz.cvut.fel.x36tjv.managedbeans.admin;

import cz.cvut.fel.x36tjv.ejbs.AdminSessionBean;
import cz.cvut.fel.x36tjv.entities.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@ManagedBean(name = "deleteCustomer")
@RequestScoped
public class DeleteCustomer {

    @EJB
    private AdminSessionBean bean;

    private Long id;

    public DeleteCustomer() {
    }

    public String delete() {
        try {
            bean.deleteCustomer(id);
        }
        catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Customer could not be deleted.");
            FacesContext.getCurrentInstance().addMessage("deleteCustomer:button", msg);
        }
        return "customers";
    }

    public List<SelectItem> getCustomers() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (Customer c : bean.getCustomers()) {
            list.add(new SelectItem(c.getId(), String.format("%s %s", c.getFirstName(), c.getLastName())));
        }
        return list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
