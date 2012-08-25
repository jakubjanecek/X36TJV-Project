package cz.cvut.fel.x36tjv.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Transfer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Bank bankFrom;

    @ManyToOne
    private Account accountFrom;

    @ManyToOne
    private Bank bankTo;

    @ManyToOne
    private Account accountTo;

    @ManyToOne
    private Currency currency;

    @Column(precision = 20, scale = 2)
    private BigDecimal amount;

    private String description;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar dateAndTime;

    public Transfer() {
    }

    public Transfer(Bank bankFrom, Account accountFrom, Bank bankTo, Account accountTo, BigDecimal amount, Currency currency,
                    String description,
                    Calendar dateAndTime) {
        this.bankFrom = bankFrom;
        this.accountFrom = accountFrom;
        this.bankTo = bankTo;
        this.accountTo = accountTo;
        this.currency = currency;
        this.amount = amount;
        this.description = description;
        this.dateAndTime = dateAndTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Bank getBankFrom() {
        return bankFrom;
    }

    public void setBankFrom(Bank bankFrom) {
        this.bankFrom = bankFrom;
    }

    public Bank getBankTo() {
        return bankTo;
    }

    public void setBankTo(Bank bankTo) {
        this.bankTo = bankTo;
    }

    public Calendar getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Calendar dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
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
        if (!(object instanceof Transfer)) {
            return false;
        }
        Transfer other = (Transfer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.fel.x36tjv.entities.Transfer[id=" + id + "]";
    }
}
