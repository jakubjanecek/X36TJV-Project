package cz.cvut.fel.x36tjv.ejbs;

import cz.cvut.fel.x36tjv.exceptions.TransferFailedException;
import cz.cvut.fel.x36tjv.entities.Account;
import cz.cvut.fel.x36tjv.entities.Bank;
import cz.cvut.fel.x36tjv.entities.BankTransaction;
import cz.cvut.fel.x36tjv.entities.Currency;
import cz.cvut.fel.x36tjv.entities.Customer;
import cz.cvut.fel.x36tjv.entities.ExchangeRate;
import cz.cvut.fel.x36tjv.ws.CentralBankService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateful;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.ws.WebServiceRef;

@Stateful
@Named("clientBean")
public class ClientSessionBean {

    @WebServiceRef(wsdlLocation = "META-INF/wsdl/localhost_8080/X36TJVCentralBankWS/CentralBankService.wsdl")
    private CentralBankService service;

    private Customer customer;

    @PersistenceContext
    private EntityManager em;

//    @Resource(mappedName = "jms/statementQueueFactory")
//    private QueueConnectionFactory connectionFactory;
//
//    @Resource(mappedName = "jms/statementQueue")
//    private Queue statementQueue;
    public void chooseCustomer(Long id) {
        customer = em.find(Customer.class, id);
    }

    public void updateDebet(Long accountId, BigDecimal debet) throws IllegalArgumentException {
        Account account = em.find(Account.class, accountId);
        if (debet.compareTo(account.getDebet()) < 0) {
            account.setDebet(debet);
        }
        else {
            throw new IllegalArgumentException("Debet must be less than before.");
        }
    }

    public boolean transfer(Long from, Short bank, Long to, BigDecimal amount, String currency, String description) throws
          IllegalArgumentException, TransferFailedException {
        Account fromAccount = em.find(Account.class, from);
        amount = amount.setScale(fromAccount.getCurrency().getNumOfDigits(), RoundingMode.HALF_UP);
        if (amount.compareTo(new BigDecimal(0L)) > 0) {
            if (!fromAccount.getCurrency().getCode().equals(currency)) {
                Currency curr = em.find(Currency.class, currency);
                BigDecimal rate = exchangeRate(curr, fromAccount.getCurrency());
                amount = amount.multiply(rate);
            }

            if (fromAccount.getBalance().add(fromAccount.getDebet()).compareTo(amount) < 0) {
                throw new TransferFailedException("Not enough money!");
            }

            fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
            Account toAccount = em.find(Account.class, to);
            BigDecimal exchangeRate = exchangeRate(fromAccount.getCurrency(), toAccount.getCurrency());
            BigDecimal newAmount = amount.multiply(exchangeRate);
            toAccount.setBalance(toAccount.getBalance().add(newAmount));

            BankTransaction transaction = new BankTransaction(fromAccount, toAccount, amount, newAmount, description, Calendar.
                  getInstance());

            List<BankTransaction> outgoing = fromAccount.getOutgoingTransactions();
            outgoing.add(transaction);
            List<BankTransaction> incoming = toAccount.getIncomingTransactions();
            incoming.add(transaction);

            em.persist(transaction);

            if (!fromAccount.getBank().getCode().equals(bank)) {
                try {
                    cz.cvut.fel.x36tjv.ws.CentralBank port = service.getCentralBankPort();
                    Account a = em.find(Account.class, from);
                    boolean result = port.transfer(a.getBank().getCode(), from, bank, to, amount, currency,
                                                   description);
                    return result;
                }
                catch (Exception ex) {
                    throw new TransferFailedException("Transfer failed.");
                }
            }
            return true;
        }
        else {
            throw new IllegalArgumentException("Amount cannot be less than zero!");
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

    public void sendStatement(Long accountID) {
//        QueueConnection connection = null;
//        try {
//            connection = connectionFactory.createQueueConnection();
//            connection.start();
//            Session session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
//            MessageProducer producer = session.createProducer(statementQueue);
//            Message message = session.createObjectMessage(accountID);
//            producer.send(message);
//        }
//        catch (JMSException ex) {
//            ex.printStackTrace();
//        }
//        finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                }
//                catch (JMSException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
    }

    public Account getAccount(Long id) {
        return em.find(Account.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Customer> getCustomers() {
        return performNamedQuery("Customer.findAll");
    }

    @SuppressWarnings("unchecked")
    public List<Account> getAccounts() {
        Query q = em.createNamedQuery("Account.findAllByCustomer");
        q.setParameter("customer", customer);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<BankTransaction> getTransactions() {
        List<BankTransaction> list = new ArrayList<BankTransaction>();
        for (Account a : customer.getAccounts()) {
            list.addAll(a.getAllTransactions());
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Bank> getBanks() {
        return performNamedQuery("Bank.findAll");
    }

    @SuppressWarnings("unchecked")
    public List<Currency> getCurrencies() {
        return performNamedQuery("Currency.findAll");
    }

    private List performNamedQuery(String name) {
        Query q = em.createNamedQuery(name);
        return q.getResultList();
    }
}
