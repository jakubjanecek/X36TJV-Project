package cz.cvut.fel.x36tjv.managedbeans.admin;

import cz.cvut.fel.x36tjv.ejbs.AdminSessionBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ManagedBean(name = "addCustomer")
@RequestScoped
public class AddCustomer {

    @EJB
    private AdminSessionBean bean;

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

    public AddCustomer() {
    }

    public String add() {
        try {
            bean.addCustomer(firstName, lastName, email);
        }
        catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Customer could not be added.");
            FacesContext.getCurrentInstance().addMessage("addCustomer:button", msg);
        }
        return "customers";
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
}
