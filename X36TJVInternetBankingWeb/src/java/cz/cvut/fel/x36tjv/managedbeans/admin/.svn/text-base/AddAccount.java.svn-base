package cz.cvut.fel.x36tjv.managedbeans.admin;

import cz.cvut.fel.x36tjv.ejbs.AdminSessionBean;
import cz.cvut.fel.x36tjv.entities.Bank;
import cz.cvut.fel.x36tjv.entities.Currency;
import cz.cvut.fel.x36tjv.entities.Customer;
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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ManagedBean(name = "addAccount")
@RequestScoped
public class AddAccount {

    @EJB
    private AdminSessionBean bean;

    @NotNull(message = "Please, specify the initial balance.")
    @Min(value = 0, message = "The balance must be a positive number.")
    @Digits(integer = 18, fraction = 2, message = "The balance must be a positive number with maximum 2 fractionals.")
    private BigDecimal balance;

    @NotNull(message = "Please, specify the debet.")
    @Min(value = 0, message = "The debet must be a positive number.")
    @Digits(integer = 18, fraction = 2, message = "The balance must be a positive number with maximum 2 fractionals.")
    private BigDecimal debet;

    @NotNull(message = "Please, choose a currency.")
    private String currency;

    @NotNull(message = "Please, choose a customer.")
    private Long customer;

    @NotNull(message = "Please, choose a bank.")
    private Short bank;

    public AddAccount() {
    }

    public String add() {
        try {
            bean.addAccount(currency, balance, debet, bank, customer);
        }
        catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Account could not be added.");
            FacesContext.getCurrentInstance().addMessage("addAccount:button", msg);
        }
        return "accounts";
    }

    public List<SelectItem> getCustomers() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (Customer c : bean.getCustomers()) {
            list.add(new SelectItem(c.getId(), String.format("%s %s", c.getFirstName(), c.getLastName())));
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getDebet() {
        return debet;
    }

    public void setDebet(BigDecimal debet) {
        this.debet = debet;
    }

    public Short getBank() {
        return bank;
    }

    public void setBank(Short bank) {
        this.bank = bank;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customerId) {
        this.customer = customerId;
    }
}
