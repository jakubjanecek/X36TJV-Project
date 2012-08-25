package cz.cvut.fel.x36tjv.managedbeans.admin;

import cz.cvut.fel.x36tjv.ejbs.AdminSessionBean;
import cz.cvut.fel.x36tjv.entities.Account;
import cz.cvut.fel.x36tjv.entities.BankTransaction;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "accountTransactions")
@RequestScoped
public class AccountTransactions {

    @EJB
    private AdminSessionBean bean;

    private Long accountId;

    private Account account;

    public AccountTransactions() {
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
