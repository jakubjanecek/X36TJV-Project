package cz.cvut.fel.x36tjv.managedbeans.admin;

import cz.cvut.fel.x36tjv.ejbs.AdminSessionBean;
import cz.cvut.fel.x36tjv.entities.Currency;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean(name = "addCurrencyRate")
@RequestScoped
public class AddCurrencyRate {

    @EJB
    private AdminSessionBean bean;

    @NotNull(message = "Please, choose a currency code.")
    @Size(min = 3, max = 3, message = "Code must have three letters.")
    private String code;

    @NotNull(message = "Please, fill in the rate.")
    @Digits(integer = 3, fraction = 2, message = "Rate must be a decimal number with maximum 2 fractionals.")
    @Max(value = 254, message = "Rate must be less than or equal to 254.")
    private BigDecimal rate;

    public AddCurrencyRate() {
    }

    public String add() {
        try {
            bean.addCurrencyRate(code, rate);
        }
        catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Interest rate could not be added.");
            FacesContext.getCurrentInstance().addMessage("addCurrencyRate:button", msg);
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

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
