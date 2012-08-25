package cz.cvut.fel.x36tjv.managedbeans.client;

import cz.cvut.fel.x36tjv.ejbs.ClientSessionBean;
import cz.cvut.fel.x36tjv.entities.Account;
import cz.cvut.fel.x36tjv.entities.BankTransaction;
import java.util.Collection;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "clientAccountTransactions")
@RequestScoped
public class AccountTransactions {

    @EJB
    private ClientSessionBean bean;

    private Long accountId;

    private Account account;

    public AccountTransactions() {
    }

    @PostConstruct
    private final void init() {
        Map<String, Object> attrs = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (attrs.containsKey("ClientSessionBean")) {
            bean = (ClientSessionBean) attrs.get("ClientSessionBean");
            if (bean == null) {
                attrs.put("ClientSessionBean", bean);
            }
        }
        else {
            attrs.put("ClientSessionBean", bean);
        }
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
        load();
    }

    public Collection<BankTransaction> getTransactions() {
        return account.getAllTransactions();
    }

    private void load() {
        this.account = bean.getAccount(accountId);
    }
}
