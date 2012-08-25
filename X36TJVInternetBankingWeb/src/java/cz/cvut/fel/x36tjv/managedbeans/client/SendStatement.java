package cz.cvut.fel.x36tjv.managedbeans.client;

import cz.cvut.fel.x36tjv.ejbs.ClientSessionBean;
import cz.cvut.fel.x36tjv.entities.Account;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@ManagedBean(name = "sendStatement")
@RequestScoped
public class SendStatement {

    @EJB
    private ClientSessionBean bean;

    private Long accountId;

    public SendStatement() {
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

    public String send() {
        bean.sendStatement(accountId);
        return null;
    }

    public List<SelectItem> getAccounts() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (Account a : bean.getAccounts()) {
            list.add(new SelectItem(a.getId()));
        }
        return list;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
