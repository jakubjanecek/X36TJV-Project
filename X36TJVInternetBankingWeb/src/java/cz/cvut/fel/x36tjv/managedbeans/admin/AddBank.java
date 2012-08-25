package cz.cvut.fel.x36tjv.managedbeans.admin;

import cz.cvut.fel.x36tjv.ejbs.AdminSessionBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean(name = "addBank")
@RequestScoped
public class AddBank {

    @EJB
    private AdminSessionBean bean;

    @NotNull(message = "Please, fill in the bank code.")
    @Digits(integer = 3, fraction = 0, message = "Bank code must be an integer with maximum three digits.")
    private Short code;

    @NotNull(message = "Please, fill in the bank name.")
    @Size(min = 1, max = 100, message = "Name of the bank must be between 1 and 100 characters long.")
    private String name;

    public AddBank() {
    }

    public String add() {
        try {
            bean.addBank(code, name);
        }
        catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Bank could not be added.");
            FacesContext.getCurrentInstance().addMessage("addBank:button", msg);
        }
        return null;
    }

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
