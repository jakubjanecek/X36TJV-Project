package cz.cvut.fel.x36tjv.managedbeans.client;

import cz.cvut.fel.x36tjv.ejbs.ClientSessionBean;
import cz.cvut.fel.x36tjv.entities.Account;
import cz.cvut.fel.x36tjv.entities.Bank;
import cz.cvut.fel.x36tjv.entities.Currency;
import cz.cvut.fel.x36tjv.exceptions.TransferFailedException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean(name = "transfer")
@RequestScoped
public class Transfer {

    @EJB
    private ClientSessionBean bean;

    @NotNull(message = "Please, choose the from account.")
    private Long fromAccountId;

    @NotNull(message = "Please, choose a bank.")
    private Short bankCode;

    @NotNull(message = "Please, specify the number of account you want to transfer money to.")
    private Long toAccountId;

    @NotNull(message = "Please, specify the amount.")
    @Min(value = 0)
    @Digits(integer = 18, fraction = 2, message = "The amount must be a positive number with maximum 2 fractionals.")
    private BigDecimal amount;

    @NotNull(message = "Please, choose a currency.")
    @Size(min = 3, max = 3, message = "Invalid currency.")
    private String currencyCode;

    @Size(min = 0, max = 300, message = "Description must be maximum 300 characters long.")
    private String description;

    public Transfer() {
    }

    @PostConstruct
    private final void init() {
        Map<String, Object> attrs = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (attrs.containsKey("ClientSessionBean")) {
            bean = (ClientSessionBean) attrs.get("ClientSessionBean");
        }
        else {
            attrs.put("ClientSessionBean", bean);
        }
    }

    public String transfer() {
        try {
            boolean result = bean.transfer(fromAccountId, bankCode, toAccountId, amount, currencyCode, description);
            if (result) {
                FacesMessage msg = new FacesMessage("Transfer successful!");
                FacesContext.getCurrentInstance().addMessage("form:transfer", msg);
            }
            else {
                FacesMessage msg = new FacesMessage("Transfer failed!");
                FacesContext.getCurrentInstance().addMessage("form:transfer", msg);
            }
        }
        catch (IllegalArgumentException ex) {
            FacesMessage msg = new FacesMessage(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage("form:transfer", msg);
        }
        catch (TransferFailedException ex) {
            FacesMessage msg = new FacesMessage(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage("form:transfer", msg);
        }
        return "transfer";
    }

    public List<SelectItem> getAccounts() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (Account a : bean.getAccounts()) {
            list.add(new SelectItem(a.getId()));
        }
        return list;
    }

    public List<SelectItem> getBanks() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (Bank b : bean.getBanks()) {
            list.add(new SelectItem(b.getCode(), b.getName()));
        }
        return list;
    }

    public List<SelectItem> getCurrencies() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (Currency c : bean.getCurrencies()) {
            list.add(new SelectItem(c.getCode(), c.getName()));
        }
        return list;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Short getBankCode() {
        return bankCode;
    }

    public void setBankCode(Short bankCode) {
        this.bankCode = bankCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }
}
