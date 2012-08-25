package cz.cvut.fel.x36tjv.managedbeans.admin;

import cz.cvut.fel.x36tjv.ejbs.AdminSessionBean;
import cz.cvut.fel.x36tjv.entities.Currency;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean(name = "deleteCurrency")
@RequestScoped
public class DeleteCurrency {

    @EJB
    private AdminSessionBean bean;

    @Size(min = 3, max = 3)
    @NotNull(message = "Please, choose a currency code.")
    private String code;

    public DeleteCurrency() {
    }

    public String delete() {
        try {
            bean.deleteCurrency(code);
        }
        catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Currency could not be deleted.");
            FacesContext.getCurrentInstance().addMessage("deleteCurrency:button", msg);
        }
        return null;
    }

    public List<SelectItem> getCurrencies() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (Currency c : bean.getCurrencies()) {
            list.add(new SelectItem(c.getCode(), c.getName()));
        }
        return list;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
