package cz.cvut.fel.x36tjv.managedbeans.client;

import cz.cvut.fel.x36tjv.ejbs.ClientSessionBean;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ManagedBean(name = "updateDebet")
@RequestScoped
public class UpdateDebet {

    @EJB
    private ClientSessionBean bean;

    private Long accountId;

    private Long id;

    @NotNull(message = "Please, specify the debet.")
    @Min(value = 0, message = "The debet must be a positive number.")
    @Digits(integer = 18, fraction = 2, message = "The balance must be a positive number with maximum 2 fractionals.")
    private BigDecimal debet;

    public UpdateDebet() {
    }

    public String update() {
        try {
            bean.updateDebet(id, debet);
        }
        catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Debet must be less than before.");
            FacesContext.getCurrentInstance().addMessage("updateDebet:button", msg);
            return null;
        }
        return "accounts";
    }

    public BigDecimal getDebet() {
        return debet;
    }

    public void setDebet(BigDecimal debet) {
        this.debet = debet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
        this.id = accountId;
        if (id != null) {
            this.debet = bean.getAccount(id).getDebet();
        }
    }
}
