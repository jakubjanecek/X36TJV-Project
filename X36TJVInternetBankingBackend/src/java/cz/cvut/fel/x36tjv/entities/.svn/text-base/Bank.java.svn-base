package cz.cvut.fel.x36tjv.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

@Entity
@NamedQuery(name = "Bank.findAll", query = "SELECT b FROM Bank b ORDER BY b.code")
public class Bank implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Short code;

    private String name;

    @OneToMany(mappedBy = "bank")
    private Collection<Account> accounts;

    public Bank() {
    }

    public Bank(Short code, String name) {
        this.code = code;
        this.name = name;
    }

    @PrePersist
    public void trimName() {
        name = name.trim();
    }

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bank)) {
            return false;
        }
        Bank other = (Bank) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.fel.x36tjv.entities.Bank[code=" + code + "]";
    }
}
