package cz.cvut.fel.x36tjv.managedbeans.admin;

import cz.cvut.fel.x36tjv.ejbs.AdminSessionBean;
import cz.cvut.fel.x36tjv.entities.CurrencyRate;
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

@ManagedBean(name = "updateCurrencyRate")
@RequestScoped
public class UpdateCurrencyRate {

    @EJB
    private AdminSessionBean bean;

    @Size(min = 3, max = 3)
    @NotNull(message = "Please, choose a currency code.")
    private String code;

    @NotNull(message = "Please, fill in the rate.")
    @Digits(integer = 3, fraction = 2, message = "Rate must be a decimal number with maximum 2 fractionals.")
    @Max(value = 254, message = "Rate must be less than or equal to 254.")
    private BigDecimal rate;

    public UpdateCurrencyRate() {
    }

    public String update() {
        try {
            bean.updateCurrencyRate(code, rate);
        }
        catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Interest rate could not be updated.");
            FacesContext.getCurrentInstance().addMessage("updateCurrencyRate:button", msg);
        }
        return null;
    }

    public List<SelectItem> getCurrencyRates() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (CurrencyRate c : bean.getCurrencyRates()) {
            list.add(new SelectItem(c.getCode()));
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
