package cz.cvut.fel.x36tjv.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "CurrencyRate.findAll", query = "SELECT c FROM CurrencyRate c ORDER BY c.code")
public class CurrencyRate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String code;

    @Column(precision = 20, scale = 2)
    private BigDecimal rate;

    public CurrencyRate() {
    }

    public CurrencyRate(String code, BigDecimal rate) {
        this.code = code;
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
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
        if (!(object instanceof CurrencyRate)) {
            return false;
        }
        CurrencyRate other = (CurrencyRate) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.fel.x36tjv.entities.CurrencyRate[code=" + code + "]";
    }
}
