package edu.gatech.seclass.tccart.utility;

import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import edu.gatech.seclass.tccart.db.Customer;
import edu.gatech.seclass.tccart.db.CustomerDao;
import edu.gatech.seclass.tccart.db.DaoMaster;
import edu.gatech.seclass.tccart.db.DaoSession;
import edu.gatech.seclass.tccart.db.Transaction;
import edu.gatech.seclass.tccart.db.TransactionDao;

/**
 * Created by bulentcoskun on 3/25/16.
 */
public  class InitialSetupHelper  {


    protected static DaoMaster daoTCCartMaster = null;

    public InitialSetupHelper(SQLiteDatabase db) {
        daoTCCartMaster = new DaoMaster(db);
    }

    public void CreateInitialRecords() throws ParseException {
        DaoSession session = daoTCCartMaster.newSession();
        CustomerDao customerDao = session.getCustomerDao();

        Customer c1 = new Customer("7c86ffee".toUpperCase());
        c1.setName("Ralph Hapschatt");
        c1.setEmail("Ralph.Hapschatt@gmail.com");
        TCCartHelpers.EnsureDefaultValues(c1);

  //      c1.setSpendBalance(10.0); //Customer has 1 translation for 2016

        Customer c2 = new Customer("b59441af".toUpperCase());
        c2.setName("Betty Monroe");
        c2.setEmail("Betty.Monroe@gmail.com");
        TCCartHelpers.EnsureDefaultValues(c2);


//        c2.setSpendBalance(51.0); //C2 has 3 transactions for 2016

        Customer c3 = new Customer("cd0f0e05".toUpperCase());
        c3.setName("Everett Scott");
        c3.setEmail("Everett.Scott@gmail.com");
        TCCartHelpers.EnsureDefaultValues(c3);

        Calendar cal = Calendar.getInstance();

        c3.setVIP(true);
        c3.setRewardBalance(3.0);
        cal.set(2016, 3, 3);
        c3.setRewardExpireDate(cal.getTime());

      //  c3.setSpendBalance(10.0); //Customer 3 has 1 translation for 2016

        customerDao.insertOrReplace(c1);
        customerDao.insertOrReplace(c2);
        customerDao.insertOrReplace(c3);

        List<Customer> customers = new ArrayList<>();

        customers.add(c1);
        customers.add(c2);
        customers.add(c3);

        createTransactionForMembers(session, customerDao,customers);
        /*uncomment this to set up database initially to test dbMaintenance()
        cal.set(2016,1,1,0,0);
        c3.setVIPDiscountBegins(cal.getTime());
        c3.setVIP(false);
        cal.set(2017,1,1,0,0);
        c2.setVIPDiscountBegins(cal.getTime());
        c2.setVIP(true);
        c1.setVIP(true);
        c3.setSpendBalance(103.00);
        c2.setSpendBalance(102.00);
        c1.setSpendBalance(101.00);
        customerDao.insertOrReplace(c1);
        customerDao.insertOrReplace(c2);
        customerDao.insertOrReplace(c3);*/

    }

    private static void createTransactionForMembers(DaoSession session, CustomerDao customerDao, List<Customer> customers) {

        Customer c1 = customers.get(0);
        Customer c2 = customers.get(1);
        Customer c3 = customers.get(2);

        Transaction t1 = null;
        Calendar cal = Calendar.getInstance();

        //C1 - Ralph Hapschatt
        cal.set(2016,1,1,13,10);
        t1 = createNewTransaction(c1,cal,10.0, 0.0,0.0, 10.0);
        session.insert(t1);


        //C2 - Betty Monroe
        cal.set(Calendar.HOUR, 17);
        cal.set(Calendar.MINUTE, 12);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Transaction t2 = createNewTransaction(c2,cal,t1);
        t2.setCartTotal(15.5);
        t2.setTotal(15.5);
        session.insert(t2);


        cal.set(Calendar.HOUR, 16);
        cal.set(Calendar.MINUTE, 13);
        cal.add(Calendar.DAY_OF_MONTH, 2);
        t2 = createNewTransaction(c2,cal,t2);
        t2.setCartTotal(23.0);
        t2.setTotal(23.0);
        session.insert(t2);

        cal.set(Calendar.HOUR, 10);
        cal.set(Calendar.MINUTE, 9);
        cal.add(Calendar.DAY_OF_MONTH, 4);
        t2 = createNewTransaction(c2,cal,t2);
        t2.setCartTotal(12.5);
        t2.setTotal(12.5);
        session.insert(t2);


        // C3 - Everett Scott past year transactions to bring VIP status
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.HOUR, 15);
        cal.set(Calendar.MINUTE, 24);
        Transaction t3 = createNewTransaction(c3,cal,t1);
        session.insert(t3);


        cal.add(Calendar.MONTH, 2);
        cal.add(Calendar.HOUR, 2);
        t3 = createNewTransaction(c3,cal,t3);
        t3.setCartTotal(150.0);
        t3.setCreditDiscount(3.0);
        t3.setTotal(150.0 - 3.0);

        session.insert(t3);


        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.HOUR, 2);
        t3 = createNewTransaction(c3,cal,t3);

        t3.setCartTotal(175.0);
        t3.setCreditDiscount(3.0);
        t3.setTotal(175.0 - 3.0);

        session.insert(t3);

        cal.set(2016,2,3,13,2);
        t3 = createNewTransaction(c3,cal,t1);
        //Because customer is already VIP when we enter this transaction.
        t3.setCartTotal(31.0);
        t3.setVIPDiscount(1.0);
        t3.setTotal(30.0);
        session.insert(t3);

        CalculateSpendingBalances(session);
    }

    private static void CalculateSpendingBalances(DaoSession session) {
        TransactionDao transactionDao = session.getTransactionDao();
        QueryBuilder<Transaction> q = transactionDao.queryBuilder();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR,0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND,0);
        Date beginningOfTheYear = cal.getTime();
        cal.add(Calendar.YEAR, 1);
        Date beginningOfTheNextYear = cal.getTime();
        List<Transaction> allTrxforThisYear = q.where(TransactionDao.Properties.TransactionDate.between(beginningOfTheYear, beginningOfTheNextYear)).list();
        Map<String, Double> customerSpendingBalance = new HashMap<String,Double>();

        for(Transaction trx : allTrxforThisYear )
        {

            if ( customerSpendingBalance.containsKey(trx.getCustomerID()) )
            {
                customerSpendingBalance.put( trx.getCustomerID(), customerSpendingBalance.get(trx.getCustomerID()) + trx.getTotal());
            }
            else
            {
                customerSpendingBalance.put(trx.getCustomerID(), trx.getTotal());
            }
        }

        for ( String customerID : customerSpendingBalance.keySet() ){
            Customer customer = session.load(Customer.class,customerID);
            customer.setSpendBalance(customerSpendingBalance.get(customerID));
            session.update(customer);
        }

    }

    private static Transaction createNewTransaction(Customer cst,
                                                    Calendar cal,
                                                 Double cartTotal,
                                                 Double creditDiscount,
                                                 Double vipDiscount,
                                                 Double total) {

        Transaction newTranslation = new Transaction();
        newTranslation.setCustomerID(cst.getUniqueID());
        newTranslation.setTransactionDate(cal.getTime());
        newTranslation.setCartTotal(cartTotal);
        newTranslation.setCreditDiscount(creditDiscount);
        newTranslation.setVIPDiscount(vipDiscount);
        newTranslation.setTotal(total);
        return newTranslation;
    }

    private static Transaction createNewTransaction(Calendar cal,
                                                    Transaction trx) {

        Transaction newTranslation = new Transaction();
        newTranslation.setTransactionDate(cal.getTime());
        newTranslation.setCartTotal(trx.getCartTotal());
        newTranslation.setCreditDiscount(trx.getCreditDiscount());
        newTranslation.setVIPDiscount(trx.getVIPDiscount());
        newTranslation.setTotal(trx.getTotal());
        return newTranslation;
    }

    private static Transaction createNewTransaction(Customer cst, Calendar cal,
                                                    Transaction trx)
    {
        Transaction newTranslation = createNewTransaction(cal, trx);
        newTranslation.setCustomerID(cst.getUniqueID());
        return newTranslation;
    }

    public static void dbMaintenance(DaoSession session) {
        /*If the VIPDiscountBegins has passed this method sets VIP to true and VIPDiscountBegins
        to null. It also recalculates spend balance with all the transactions that have taken
        place in the current year and checks if VIP status has been reached for next year.*/
        //DaoSession session = daoTCCartMaster.newSession();
        CustomerDao customerDao = session.getCustomerDao();
        TransactionDao transactionDao = session.getTransactionDao();
        double LastYearSpendingBalance;
        double ThisYearSpendingBlanace;

        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR,0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND,0);
        Date firstDateOfThisYear = cal.getTime();
        cal.add(Calendar.YEAR, 1);
        Date firstDateOfTheNextYear = cal.getTime();
        cal.add(Calendar.YEAR, -2);
        Date firstDateOfTheLastYear = cal.getTime();

        CalculateSpendingBalances(session);
        QueryBuilder<Customer> qc = customerDao.queryBuilder();
        QueryBuilder<Transaction> qt;
        List<Customer> allCustomers = qc.list();

        for(Customer c : allCustomers) {

            //else check if they spent $300 last year, if not set VIP status to false.
            LastYearSpendingBalance = 0.00;
            ThisYearSpendingBlanace = 0.00;

            qt = transactionDao.queryBuilder();

            List<Transaction> allTrxForLastYear;
            List<Transaction> allTrxForThisYear;

            allTrxForLastYear = qt.where(
                            qt.and( TransactionDao.Properties.TransactionDate.between(firstDateOfTheLastYear, firstDateOfThisYear),
                            TransactionDao.Properties.CustomerID.like(c.getUniqueID()))
                    ).list();


            qt = transactionDao.queryBuilder();

            allTrxForThisYear = qt.where(
                    qt.and( TransactionDao.Properties.TransactionDate.between( firstDateOfThisYear, firstDateOfTheNextYear),
                        TransactionDao.Properties.CustomerID.like(c.getUniqueID()))
                        ).list();


            for(Transaction trx : allTrxForLastYear)
            {
                LastYearSpendingBalance += trx.getTotal();
            }

            c.setVIPDiscountBegins(null);

            if (LastYearSpendingBalance < 300.00) {
                c.setVIP(false);
            }else
            {
                c.setVIP(true);
            }


            for(Transaction trx : allTrxForThisYear)
            {
                ThisYearSpendingBlanace += trx.getTotal();
            }

            if ( ThisYearSpendingBlanace >= 300)
            {
                c.setVIPDiscountBegins(firstDateOfTheNextYear);
            }

            c.setSpendBalance(ThisYearSpendingBlanace);

            session.insertOrReplace(c);
        }
    }
}
