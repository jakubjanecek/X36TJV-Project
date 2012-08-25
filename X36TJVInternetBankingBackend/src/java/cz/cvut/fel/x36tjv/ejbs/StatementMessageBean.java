package cz.cvut.fel.x36tjv.ejbs;

import cz.cvut.fel.x36tjv.entities.Account;
import java.util.Properties;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@MessageDriven(mappedName = "jms/statementQueue", activationConfig = {
//    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
//    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
//})
public class StatementMessageBean implements MessageListener {

    @PersistenceContext
    private EntityManager em;

    public StatementMessageBean() {
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            Long id = null;
            try {
                id = (Long) ((ObjectMessage) message).getObject();
                Account account = em.find(Account.class, id);
                String email = account.getCustomer().getEmail();

                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.mail.yahoo.it");
                props.put("mail.smtp.port", "25");
                Session session = Session.getDefaultInstance(props, null);
                javax.mail.Message emailMessage = new MimeMessage(session);
                emailMessage.setFrom(new InternetAddress("statements@cvutbank.cz"));
                emailMessage.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(email));
                emailMessage.setSubject("Statement for account number " + id);
                // TODO fill in transactions
                emailMessage.setText("");
                Transport.send(emailMessage);
            }
            catch (JMSException ex) {
                ex.printStackTrace();
            }
            catch (MessagingException ex) {
                ex.printStackTrace();
            }
        }
    }
}
