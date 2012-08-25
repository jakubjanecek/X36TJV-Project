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
import org.hibernate.validator.constraints.Range;

@ManagedBean(name = "updateCurrency")
@RequestScoped
public class UpdateCurrency {

    @EJB
    private AdminSessionBean bean;

    @Size(min = 3, max = 3)
    @NotNull(message = "Please, choose a currency code.")
    private String code;

    @NotNull(message = "Please, fill in the name of the currency.")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters long.")
    private String name;

    @NotNull(message = "Please, fill in the number of digits.")
    @Range(min = 0, max = 2, message = "The currency can have 0, 1 or 2 digits.")
    private Byte numOfDigits;

    public UpdateCurrency() {
    }

    public String update() {
        try {
            bean.updateCurrency(code, name, numOfDigits);
        }
        catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Currency could not be updated.");
            FacesContext.getCurrentInstance().addMessage("updateCurrency:button", msg);
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

    public List<SelectItem> getPossibleNumbersOfDigits() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        list.add(new SelectItem(0));
        list.add(new SelectItem(1));
        list.add(new SelectItem(2));
        return list;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getNumOfDigits() {
        return numOfDigits;
    }

    public void setNumOfDigits(Byte numOfDigits) {
        this.numOfDigits = numOfDigits;
    }
}
