package cz.cvut.fel.x36tjv.managedbeans.admin;

import cz.cvut.fel.x36tjv.ejbs.AdminSessionBean;
import cz.cvut.fel.x36tjv.entities.Customer;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ManagedBean(name = "updateCustomer")
@RequestScoped
public class UpdateCustomer {

    @EJB
    private AdminSessionBean bean;

    private Long id;

    private Long customerId;

    @NotNull(message = "Please, fill in the first name.")
    @Size(min = 1, max = 40, message = "The first name must be between 1 and 50 characters long.")
    private String firstName;

    @NotNull(message = "Please, fill in the last name.")
    @Size(min = 1, max = 40, message = "The last name must be between 1 and 50 characters long.")
    private String lastName;

    @NotNull(message = "Please, fill in the e-mail.")
    @Size(min = 1, max = 200, message = "The e-mail must be between 1 and 50 characters long.")
    @Pattern(regexp = "^[a-z0-9A-Z_\\.-]+@[a-z0-9A-Z_\\.-]+\\.cz$",
             message = "The e-mail is invalid. It must be a valid e-mail address from .cz domain.")
    private String email;

    private Long version;

    public UpdateCustomer() {
    }

    public String update() {
        try {
            bean.updateCustomer(id, firstName, lastName, email, version);
        }
        catch (Exception ex) {
            FacesMessage msg;
            if (ex.getMessage().startsWith("alreadyUpdated")) {
                msg = new FacesMessage("Customer could not be updated because somebody already updated it.");
            }
            else {
                msg = new FacesMessage("Customer could not be updated.");
            }
            FacesContext.getCurrentInstance().addMessage("updateCustomer:button", msg);
            return null;
        }
        return "customers";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long id) {
        this.customerId = id;
        this.id = id;
        if (id != null) {
            Customer c = bean.getCustomer(id);
            firstName = c.getFirstName();
            lastName = c.getLastName();
            email = c.getEmail();
            version = c.getVersion();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
