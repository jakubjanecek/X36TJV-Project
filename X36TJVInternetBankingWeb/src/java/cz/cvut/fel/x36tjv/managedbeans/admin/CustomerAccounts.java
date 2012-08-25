package cz.cvut.fel.x36tjv.managedbeans.admin;

import cz.cvut.fel.x36tjv.ejbs.AdminSessionBean;
import cz.cvut.fel.x36tjv.entities.Account;
import cz.cvut.fel.x36tjv.entities.Customer;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "customerAccounts")
@RequestScoped
public class CustomerAccounts {

    @EJB
    private AdminSessionBean bean;

    private Long customerId;

    private Customer customer;

    public CustomerAccounts() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
        load();
    }

    public Collection<Account> getAccounts() {
        return customer.getAccounts();
    }

    private void load() {
        this.customer = bean.getCustomer(customerId);
    }
}
