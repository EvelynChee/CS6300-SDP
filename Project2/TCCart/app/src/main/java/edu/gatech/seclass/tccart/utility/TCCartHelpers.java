package edu.gatech.seclass.tccart.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import de.greenrobot.dao.query.QueryBuilder;
import edu.gatech.seclass.tccart.db.Customer;
import edu.gatech.seclass.tccart.db.CustomerDao;

/**
 * Created by bulentcoskun on 3/23/16.
 */
public final class TCCartHelpers {

    public static String getUniqueIDByEmail(CustomerDao customerDao, Customer customer) {

        QueryBuilder<Customer> query = customerDao.queryBuilder().where(CustomerDao.Properties.Email.like(customer.getEmail()));
        List<Customer> result = query.list();
        if (result.isEmpty()) return "";

        return result.get(0).getUniqueID();
    }

    public static void EnsureDefaultValues(Customer current) {

        if (current.getRewardBalance() == null) current.setRewardBalance(0.0);

        if (current.getVIP() == null) current.setVIP(false);

        if (current.getRewardExpireDate() == null) {
            // I am not sure about this value!
        }

        if (current.getSpendBalance() == null) current.setSpendBalance(0.0);

        if (current.getVIPNotificationSent() == null) current.setVIPNotificationSent(false);

        if (current.getVIPDiscountBegins() == null) {
            // I am not sure we should value on this field
        }
    }

    public static String FormatDate(Date value){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        if (value == null) return "-";

        return formatter.format(value);
    }


    public static Date firstDayOfNextYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.YEAR, 1);
        return calendar.getTime();
    }
}
