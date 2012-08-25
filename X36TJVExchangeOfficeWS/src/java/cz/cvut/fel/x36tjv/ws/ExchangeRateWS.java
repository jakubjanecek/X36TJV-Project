package cz.cvut.fel.x36tjv.ws;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import cz.cvut.fel.x36tjv.entities.ExchangeRate;
import java.math.RoundingMode;
import javax.ejb.Stateless;

@Path("/ExchangeRate/")
@Stateless
public class ExchangeRateWS {

    @PersistenceContext
    private EntityManager em;

    @GET
    @Path("exchangerate/{fromto}")
    @Produces({"text/plain"})
    public String exchangerate(@PathParam("fromto") final String fromto) {
        if (fromto.matches("^[A-Z]{6}$")) {
            String from = fromto.substring(0, 3);
            String to = fromto.substring(3, 6);
            ExchangeRate rate1 = em.find(ExchangeRate.class, from);
            ExchangeRate rate2 = em.find(ExchangeRate.class, to);
            if (rate1 == null || rate2 == null) {
                return "-1";
            }
            return rate1.getRate().divide(rate2.getRate(), 4, RoundingMode.HALF_UP).toString();
//            return from + " - " + to + ": " + rate1.getRate().divide(rate2.getRate(), 4, RoundingMode.HALF_UP);
        }
        else {
            return "-1";
        }
    }
}
