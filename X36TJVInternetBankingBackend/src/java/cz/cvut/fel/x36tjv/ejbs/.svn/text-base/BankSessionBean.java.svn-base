package cz.cvut.fel.x36tjv.ejbs;

import cz.cvut.fel.x36tjv.entities.Account;
import cz.cvut.fel.x36tjv.entities.BankTransaction;
import cz.cvut.fel.x36tjv.entities.Currency;
import cz.cvut.fel.x36tjv.entities.CurrencyRate;
import cz.cvut.fel.x36tjv.entities.CurrentCurrencyRate;
import cz.cvut.fel.x36tjv.entities.ExchangeRate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named("bankBean")
@Stateless
public class BankSessionBean {

    private static final Long bankAccountID = new Long(1);

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Schedule
    public void payInterest() {
        List<Account> accounts = (List<Account>) performNamedQuery("Account.findAll");
        for (Account a : accounts) {
            BigDecimal minBalance = getMinBalance(a);
            CurrentCurrencyRate rate = getCurrencyRate(a);
            if (rate != null) {
                BigDecimal interest = minBalance.multiply(rate.getRate().divide(new BigDecimal(100)));
                transfer(a, interest);
            }
        }
        copyCurrentRates();
    }

    private BigDecimal getMinBalance(Account a) {
        BigDecimal balance = a.getBalance();
        BigDecimal min = new BigDecimal(balance.toBigInteger());
        List<BankTransaction> incoming = a.getIncomingTransactions();
        for (BankTransaction t : incoming) {
            if (yesterday(t.getDateAndTime())) {
                balance = balance.add(t.getAmountTo());
                min = balance.min(min);
            }
        }
        List<BankTransaction> outgoing = a.getOutgoingTransactions();
        for (BankTransaction t : outgoing) {
            if (yesterday(t.getDateAndTime())) {
                balance = balance.subtract(t.getAmountFrom());
                min = balance.min(min);
            }
        }
        return min;
    }

    private CurrentCurrencyRate getCurrencyRate(Account a) {
        @SuppressWarnings("unchecked")
        List<CurrentCurrencyRate> currentRates = (List<CurrentCurrencyRate>) performNamedQuery("CurrentCurrencyRate.findAll");
        for (CurrentCurrencyRate cr : currentRates) {
            if (cr.getCode().equals(a.getCurrency().getCode())) {
                return cr;
            }
        }
        return null;
    }

    private boolean yesterday(Calendar c) {
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);
        if (yesterday.compareTo(c) < 0) {
            return true;
        }
        return false;
    }

    private void transfer(Account account, BigDecimal interest) {
        account.setBalance(account.getBalance().add(interest));
        BigDecimal interestInCZK = interest.multiply(exchangeRate(account.getCurrency(), em.find(Currency.class, "CZK")));
        BankTransaction transaction = new BankTransaction(em.find(Account.class, BankSessionBean.bankAccountID), account,
                                                          interestInCZK, interest, "interest", Calendar.getInstance());
        List<BankTransaction> incoming = account.getIncomingTransactions();
        incoming.add(transaction);
        em.persist(transaction);
    }

    @SuppressWarnings("unchecked")
    private void copyCurrentRates() {
        List<CurrencyRate> rates = (List<CurrencyRate>) performNamedQuery("CurrencyRate.findAll");
        List<CurrentCurrencyRate> currentRates = (List<CurrentCurrencyRate>) performNamedQuery("CurrentCurrencyRate.findAll");
        for (CurrentCurrencyRate ccr : currentRates) {
            em.remove(ccr);
        }
        em.flush();
        for (CurrencyRate cr : rates) {
            CurrentCurrencyRate currentRate = new CurrentCurrencyRate(cr);
            em.persist(currentRate);
        }
    }

    private BigDecimal exchangeRate(Currency from, Currency to) {
        ExchangeRate rate1 = em.find(ExchangeRate.class, from.getCode());
        ExchangeRate rate2 = em.find(ExchangeRate.class, to.getCode());
        if (rate1 == null || rate2 == null) {
            return null;
        }
        return rate1.getRate().divide(rate2.getRate(), 4, RoundingMode.HALF_UP);
    }

    private List<?> performNamedQuery(String name) {
        Query q = em.createNamedQuery(name);
        return q.getResultList();
    }
}
