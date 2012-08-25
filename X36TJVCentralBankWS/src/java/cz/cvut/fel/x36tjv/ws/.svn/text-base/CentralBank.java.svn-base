package cz.cvut.fel.x36tjv.ws;

import cz.cvut.fel.x36tjv.entities.Account;
import cz.cvut.fel.x36tjv.entities.Bank;
import cz.cvut.fel.x36tjv.entities.Currency;
import cz.cvut.fel.x36tjv.entities.Transfer;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@WebService()
@Stateless
public class CentralBank {

    @PersistenceContext
    private EntityManager em;

    public CentralBank() {
    }

    @WebMethod(operationName = "transfer")
    public Boolean transfer(@WebParam(name = "fromBank")
          final Short fromBank,
                            @WebParam(name = "fromAccount")
          final Long fromAccount,
                            @WebParam(name = "toBank")
          final Short toBank,
                            @WebParam(name = "toAccount")
          final Long toAccount,
                            @WebParam(name = "amount")
          final BigDecimal amount,
                            @WebParam(name = "currency")
          final String currency,
                            @WebParam(name = "description")
          final String description) throws IllegalArgumentException {

        Bank bankFrom = em.find(Bank.class, fromBank);
        Account accountFrom = em.find(Account.class, fromAccount);
        Bank bankTo = em.find(Bank.class, toBank);
        Account accountTo = em.find(Account.class, toAccount);
        Currency curr = em.find(Currency.class, currency);
        if (bankFrom != null && accountFrom != null && bankTo != null && accountTo != null && curr != null) {
            Transfer transfer = new Transfer(bankFrom, accountFrom, bankTo, accountTo, amount, curr, description,
                                             Calendar.getInstance());
            em.persist(transfer);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
