package cz.cvut.fel.x36tjv.managedbeans.admin;

import cz.cvut.fel.x36tjv.ejbs.AdminSessionBean;
import cz.cvut.fel.x36tjv.entities.Bank;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@ManagedBean(name = "deleteBank")
@RequestScoped
public class DeleteBank {

    @EJB
    private AdminSessionBean bean;

    @NotNull(message = "Please, choose a bank code.")
    @Digits(integer = 3, fraction = 0, message = "Bank code must be an integer with maximum three digits.")
    private Short code;

    public DeleteBank() {
    }

    public String delete() {
        try {
            bean.deleteBank(code);
        }
        catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Bank could not be deleted.");
            FacesContext.getCurrentInstance().addMessage("deleteBank:button", msg);
        }
        return null;
    }

    public List<SelectItem> getBanks() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (Bank b : bean.getBanks()) {
            list.add(new SelectItem(b.getCode(), b.getName()));
        }
        return list;
    }

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }
}
