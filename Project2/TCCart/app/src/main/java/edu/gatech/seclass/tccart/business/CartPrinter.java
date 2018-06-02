package edu.gatech.seclass.tccart.business;

import edu.gatech.seclass.tccart.db.Customer;

/**
 * Created by bulentcoskun on 4/3/16.
 */
public final class CartPrinter {

    public static boolean PrintCustomerCard(Customer c)
    {
        String name = c.getName();
        String uniqueID = c.getUniqueID();
        String[] splitName = name.split("\\s+");

        String  firstName = "";
        String  lastName = "";

        for(String str: splitName){
            if (firstName.equals("") ) firstName = str;
            else
            {
                lastName += str + " ";
            }
        }

        return edu.gatech.seclass.services.PrintingService.printCard(firstName.trim(),lastName.trim(),uniqueID);

    }

}
