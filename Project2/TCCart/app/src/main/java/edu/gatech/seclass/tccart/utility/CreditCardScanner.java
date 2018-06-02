package edu.gatech.seclass.tccart.utility;

import edu.gatech.seclass.services.CreditCardService;
import edu.gatech.seclass.tccart.business.CreditCard;

/**
 * Created by bulentcoskun on 4/5/16.
 */
public final class CreditCardScanner {

    public static CreditCard ReadCreditCard(){
        CreditCard creditCard = new CreditCard();

        String readID = CreditCardService.readCreditCard(); //returns firstname#lastname#16digccnumber#MMDDYYYY#3digCVV
        //check if valid or error

        if (null == readID || "".equals(readID) || readID.equals("ERR") ) {
            //ShowMessage("Credit Card Swipe Error");
            return null;
        }


        //ShowMessage("Credit Card Swipe Successful");
        String[] pmtInfo = readID.split("#");
        creditCard.FirstName  = pmtInfo[0];
        creditCard.LastName = pmtInfo[1];
        creditCard.AccountNumber  = pmtInfo[2];
        String date =  pmtInfo[3];
        creditCard.SecurityCode = pmtInfo[4];

        String month = date.substring(0,2);
        String day = date.substring(2,4);
        String year = date.substring(4,8);

        creditCard.ExpirationDate_Month = month;
        creditCard.ExpirationDate_Day = day;
        creditCard.ExpirationDate_Year = year;

        creditCard.ExpirationDate  =  month + "/" + day + "/" + year;
        return creditCard;
    }
}
