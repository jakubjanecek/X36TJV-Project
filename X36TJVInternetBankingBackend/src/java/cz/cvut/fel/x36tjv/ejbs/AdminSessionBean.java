package cz.cvut.fel.x36tjv.ejbs;

import cz.cvut.fel.x36tjv.entities.Account;
import cz.cvut.fel.x36tjv.entities.Bank;
import cz.cvut.fel.x36tjv.entities.BankTransaction;
import cz.cvut.fel.x36tjv.entities.Currency;
import cz.cvut.fel.x36tjv.entities.CurrencyRate;
import cz.cvut.fel.x36tjv.entities.Customer;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Named("manager")
public class AdminSessionBean {

    @PersistenceContext
    private EntityManager em;

    public void addCurrencyRate(String code, BigDecimal rate) throws Exception {
        CurrencyRate cr = new CurrencyRate(code, rate);
        em.persist(cr);
    }

    public void updateCurrencyRate(String code, BigDecimal rate) throws Exception {
        CurrencyRate cr = em.find(CurrencyRate.class, code);
        cr.setRate(rate);
    }

    public void deleteCurrencyRate(String code) throws Exception {
        CurrencyRate cr = em.find(CurrencyRate.class, code);
        em.remove(cr);
    }

    public void addCurrency(String code, String name, Byte numOfDigits) throws Exception {
        Currency c = new Currency(code, name, numOfDigits);
        em.persist(c);
    }

    public void updateCurrency(String code, String name, Byte numOfDigits) throws Exception {
        Currency c = em.find(Currency.class, code);
        c.setName(name);
        c.setNumOfDigits(numOfDigits);
    }

    public void deleteCurrency(String code) throws Exception {
        Currency c = em.find(Currency.class, code);
        em.remove(c);
    }

    public void addBank(Short code, String name) throws Exception {
        Bank b = new Bank(code, name);
        em.persist(b);
    }

    public void updateBank(Short code, String name) throws Exception {
        Bank b = em.find(Bank.class, code);
        b.setName(name);
    }

    public void deleteBank(Short code) throws Exception {
        Bank b = em.find(Bank.class, code);
        em.remove(b);
    }

    public void addCustomer(String firstName, String lastName, String email) throws Exception {
        Customer c = new Customer(firstName, lastName, email);
        em.persist(c);
    }

    public void updateCustomer(Long id, String firstName, String lastName, String email, Long version) throws Exception {
        try {
            Customer customer = new Customer(firstName, lastName, email);
            customer.setId(id);
            customer.setVersion(version);
            em.merge(customer);
        }
        catch (OptimisticLockException ex) {
            throw new Exception("alreadyUpdated");
        }
    }

    public void deleteCustomer(Long id) throws Exception {
        Customer c = em.find(Customer.class, id);
        em.remove(c);
    }

    public void addAccount(String currency, BigDecimal balance, BigDecimal debet, Short bankId, Long customerId) throws Exception {
        Account a = new Account(em.find(Currency.class, currency), balance, debet);
        a.setBank(em.find(Bank.class, bankId));
        a.setCustomer(em.find(Customer.class, customerId));
        em.persist(a);
    }

    public void updateAccount(Long accountId, BigDecimal debet) {
        Account account = em.find(Account.class, accountId);
        account.setDebet(debet);
    }

    public void deleteAccount(Long id) throws Exception {
        Account a = em.find(Account.class, id);
        em.remove(a);
    }

    public Customer getCustomer(Long id) {
        return em.find(Customer.class, id);
    }

    public Account getAccount(Long id) {
        return em.find(Account.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Account> getAccounts() {
        return performNamedQuery("Account.findAll");
    }

    @SuppressWarnings("unchecked")
    public List<Bank> getBanks() {
        return performNamedQuery("Bank.findAll");
    }

    @SuppressWarnings("unchecked")
    public List<Currency> getCurrencies() {
        return performNamedQuery("Currency.findAll");
    }

    @SuppressWarnings("unchecked")
    public List<BankTransaction> getTransactions() {
        return performNamedQuery("BankTransaction.findAllOrderedByDate");
    }

    @SuppressWarnings("unchecked")
    public List<Customer> getCustomers() {
        return performNamedQuery("Customer.findAll");
    }

    @SuppressWarnings("unchecked")
    public List<CurrencyRate> getCurrencyRates() {
        return performNamedQuery("CurrencyRate.findAll");
    }

    public Integer getNumOfBanks() {
        return performNamedQuery("Bank.findAll").size();
    }

    public Integer getNumOfCustomers() {
        return performNamedQuery("Customer.findAll").size();
    }

    public Integer getNumOfAccounts() {
        return performNamedQuery("Account.findAll").size();
    }

    public Integer getNumOfTransactions() {
        return performNamedQuery("BankTransaction.findAllOrderedByDate").size();
    }

    public Integer getNumOfCurrencies() {
        return performNamedQuery("Currency.findAll").size();
    }

    public Integer getNumOfRates() {
        return performNamedQuery("CurrencyRate.findAll").size();
    }

    private List performNamedQuery(String name) {
        Query q = em.createNamedQuery(name);
        return q.getResultList();
    }
}
