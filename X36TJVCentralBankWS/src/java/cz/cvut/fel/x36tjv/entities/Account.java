package cz.cvut.fel.x36tjv.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

@Entity
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a ORDER BY a.id"),
    @NamedQuery(name = "Account.findAllByCustomer", query = "SELECT a FROM Account a WHERE a.customer = :customer ORDER BY a.id")
})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Currency currency;

    @Column(precision = 20, scale = 2)
    private BigDecimal balance;

    @Column(precision = 20, scale = 2)
    private BigDecimal debet;

    @OneToMany(mappedBy = "accountFrom")
    private List<BankTransaction> outgoingTransactions;

    @OneToMany(mappedBy = "accountTo")
    private List<BankTransaction> incomingTransactions;

    @ManyToOne
    private Bank bank;

    @ManyToOne
    private Customer customer;

    public Account() {
    }

    public Account(Currency currency, BigDecimal balance, BigDecimal debet) {
        this.currency = currency;
        this.balance = balance;
        this.debet = debet;
    }

    @PrePersist
    public void roundBalance() {
        balance = balance.setScale(currency.getNumOfDigits(), RoundingMode.HALF_UP);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<BankTransaction> getIncomingTransactions() {
        return incomingTransactions;
    }

    public void setIncomingTransactions(List<BankTransaction> incomingTransactions) {
        this.incomingTransactions = incomingTransactions;
    }

    public List<BankTransaction> getOutgoingTransactions() {
        return outgoingTransactions;
    }

    public void setOutgoingTransactions(List<BankTransaction> outgoingTransactions) {
        this.outgoingTransactions = outgoingTransactions;
    }

    public List<BankTransaction> getAllTransactions() {
        List<BankTransaction> list = new ArrayList<BankTransaction>();
        list.addAll(incomingTransactions);
        list.addAll(outgoingTransactions);
        return list;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.fel.x36tjv.entities.Account[id=" + id + "]";
    }
}
