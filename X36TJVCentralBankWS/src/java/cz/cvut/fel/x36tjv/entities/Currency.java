package cz.cvut.fel.x36tjv.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Currency.findAll", query = "SELECT c FROM Currency c ORDER BY c.code")
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String code;

    private String name;

    private Byte numOfDigits;

    public Currency() {
    }

    public Currency(String code, String name, Byte numOfDigits) {
        this.code = code;
        this.name = name;
        this.numOfDigits = numOfDigits;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getNumOfDigits() {
        return numOfDigits;
    }

    public void setNumOfDigits(Byte numOfDigits) {
        this.numOfDigits = numOfDigits;
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
        if (!(object instanceof Currency)) {
            return false;
        }
        Currency other = (Currency) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.fel.x36tjv.entities.Currency[code=" + code + "]";
    }
}
