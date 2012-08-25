package cz.cvut.fel.x36tjv.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;

@Entity
@NamedQuery(name = "BankTransaction.findAllOrderedByDate", query = "SELECT t FROM BankTransaction t ORDER BY t.dateAndTime")
public class BankTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Account accountFrom;

    @ManyToOne
    private Account accountTo;

    @Column(precision = 20, scale = 2)
    private BigDecimal amountFrom;

    @Column(precision = 20, scale = 2)
    private BigDecimal amountTo;

    private String description;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar dateAndTime;

    public BankTransaction() {
    }

    @PrePersist
    public void roundAndTrim() {
        amountFrom = amountFrom.setScale(accountFrom.getCurrency().getNumOfDigits(), RoundingMode.HALF_UP);
        amountTo = amountTo.setScale(accountTo.getCurrency().getNumOfDigits(), RoundingMode.HALF_UP);
        description = description.trim();
    }

    public BankTransaction(Account accountFrom, Account accountTo, BigDecimal amountFrom, BigDecimal amountTo, String description,
                           Calendar dateAndTime) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amountFrom = amountFrom;
        this.amountTo = amountTo;
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

    public BigDecimal getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(BigDecimal amount) {
        this.amountFrom = amount;
    }

    public BigDecimal getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(BigDecimal amount) {
        this.amountTo = amount;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BankTransaction)) {
            return false;
        }
        BankTransaction other = (BankTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.fel.x36tjv.entities.BankTransaction[id=" + id + "]";
    }
}
