package edu.gatech.seclass.tccart.business;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

import edu.gatech.seclass.tccart.db.Customer;
import edu.gatech.seclass.tccart.db.CustomerDao;
import edu.gatech.seclass.tccart.db.DaoMaster;
import edu.gatech.seclass.tccart.db.DaoSession;
import edu.gatech.seclass.tccart.db.Transaction;
import edu.gatech.seclass.tccart.utility.InitialSetupHelper;
import edu.gatech.seclass.tccart.utility.TCCartHelpers;
import edu.gatech.seclass.tccart.utility.VideoCam;

/**
 * Created by bulentcoskun on 4/3/16.
 */
public class CartManager implements Serializable {

    private Customer currentCustomer;
    private Transaction currentTransaction;

    private transient SQLiteDatabase TCCartDB;
    private transient DaoMaster daoTCCartMaster;
    private transient Context ctx;

    public CartManager(Context appCtx){
        ctx = appCtx;

        TCCartDB = ctx.openOrCreateDatabase("tccart.db",Context.MODE_PRIVATE, null);
        DaoMaster.createAllTables(TCCartDB, true);
        daoTCCartMaster = new DaoMaster(TCCartDB);
        currentCustomer = null;
        currentTransaction = null;
    }

    public void setCurrentCustomer(Customer cCustomer){
        currentCustomer = cCustomer;
    }

    public Customer getCurrentCustomer(){
        return currentCustomer;
    }

    public void setCurrentTransaction(Transaction trx){
        currentTransaction = trx;
    }

    public Transaction getCurrentTransaction(){
        return currentTransaction;
    }

    public Customer EditCustomerName(Customer c, String newName){
        //We are using AddNewOrEditCustomer
        return currentCustomer;
    }

    public Customer EditCustomerEmail(Customer c, String newEmail ){
        //We are using AddNewOrEditCustomer
        return currentCustomer;
    }

    public Customer AddNewOrEditCustomer(String UniqueID, String c_name, String c_email){

        currentCustomer = null;

        Customer customer = new Customer();
        customer.setUniqueID(UniqueID);
        customer.setName(c_name);
        customer.setEmail(c_email);

        DaoSession session = daoTCCartMaster.newSession();
        CustomerDao customerDao = session.getCustomerDao();
        String existingCustomerUniqueID = TCCartHelpers.getUniqueIDByEmail(customerDao, customer);

        if (existingCustomerUniqueID.equals("") || existingCustomerUniqueID.equals(customer.getUniqueID())) {

            TCCartHelpers.EnsureDefaultValues(customer);
            customerDao.insertOrReplace(customer);
            currentCustomer = customer;
        }

        return currentCustomer;
    }

    public String LocateCustomerWithCustomerCard() {
        String scannedID = VideoCam.ReadCustomerCard().getID();

        DaoSession session = daoTCCartMaster.newSession();
        CustomerDao customerDao = session.getCustomerDao();
        currentCustomer = customerDao.load(scannedID.toUpperCase());
        return scannedID;
    }

    public boolean PrintCustomerCard() {
       return CartPrinter.PrintCustomerCard(currentCustomer);
    }

    public void CheckInitialSetup() {

        //Try to identify first Initial Database Setup
        try {
            Log.i("TCCart","Checking Initial Setup...");
            ctx.openFileInput("InitialSetup.done");
        }catch (FileNotFoundException e){

            try {
                ctx.openFileOutput("InitialSetup.done", Context.MODE_PRIVATE);
                Log.i("TCCart","Basic Customer Information Setup Started...");

                new InitialSetupHelper(TCCartDB).CreateInitialRecords();

                Log.i("TCCart","Basic Customer Information Setup Completed...");
            } catch (Exception e1) {
                e1.printStackTrace();

            }
        }
    }

    public List<Transaction> getCurrentCustomerTransactions() {
        if ( currentCustomer == null) return null;
        DaoSession session = daoTCCartMaster.newSession();
        session.refresh(currentCustomer);

        return currentCustomer.getTransactions();
    }

    public void saveCurrentCustomer() {
        if ( currentCustomer != null ) {
            DaoSession session = daoTCCartMaster.newSession();
            session.update(currentCustomer);
        }
    }

    public void saveCurrentTransaction(){
        if ( currentTransaction != null ) {
            DaoSession session = daoTCCartMaster.newSession();
            session.insertOrReplace(currentTransaction);
        }
    }

    public void databaseMaintenance() {
        DaoSession session = daoTCCartMaster.newSession();
        InitialSetupHelper.dbMaintenance(session);

    }
}
