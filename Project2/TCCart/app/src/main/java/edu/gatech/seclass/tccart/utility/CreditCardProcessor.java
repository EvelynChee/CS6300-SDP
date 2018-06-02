package edu.gatech.seclass.tccart.utility;

import java.util.Calendar;
import java.util.Date;

import edu.gatech.seclass.services.PaymentService;
import edu.gatech.seclass.tccart.business.CreditCard;
import edu.gatech.seclass.tccart.db.Transaction;

/**
 * Created by bulentcoskun on 4/5/16.
 */
public final class CreditCardProcessor {

    public static boolean ProcessPurchase(Transaction t, CreditCard cc){

        double orderTotal = t.getTotal();
        double cartTotal = t.getCartTotal();

        if ( orderTotal > 0 ) {
            if (cc == null) {
                return false;
            }
        }

        Calendar c1 = Calendar.getInstance();

        if ( orderTotal > 0 && cartTotal > 0) {

            c1.set(Integer.parseInt(cc.ExpirationDate_Year),
                    Integer.parseInt(cc.ExpirationDate_Month ),
                    Integer.parseInt(cc.ExpirationDate_Day));

            Date sDate = c1.getTime();

            if (PaymentService.processPayment(cc.FirstName, cc.LastName, cc.AccountNumber, sDate, cc.SecurityCode, t.getTotal())) {
                return true;
            } else {
               return false;
            }
        }

        return true;
    }
}
