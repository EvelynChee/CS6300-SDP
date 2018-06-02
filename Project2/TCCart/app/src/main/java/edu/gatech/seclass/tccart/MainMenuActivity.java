package edu.gatech.seclass.tccart;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import edu.gatech.seclass.services.*;
import de.greenrobot.dao.query.QueryBuilder;
import edu.gatech.seclass.tccart.business.CartManager;
import edu.gatech.seclass.tccart.db.Customer;
import edu.gatech.seclass.tccart.db.CustomerDao;
import edu.gatech.seclass.tccart.db.DaoSession;
import edu.gatech.seclass.tccart.utility.InitialSetupHelper;

public class MainMenuActivity extends DBActivityBase {

    private static final int VIEW_CUSTOMER_REQUEST_CODE = 1002;
    private static final int NEW_CUSTOMER_REQUEST_CODE = 1001;

    private TextView myTextCurrentCustomer;
    private TextView myTextUniqueID;
    private Button btnCreateOrder;
    private Button btnCustomerManagement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreateBase(savedInstanceState);

        setContentView(R.layout.activity_main_menu);
        myTextCurrentCustomer = (TextView)findViewById(R.id.textCurrentCustomer);
        myTextUniqueID = (TextView)findViewById(R.id.textUniqueID);
        btnCreateOrder = (Button) findViewById(R.id.buttonCreateOrder);
        btnCustomerManagement = (Button) findViewById(R.id.buttonCustomerManagement);

        ExtractCartManagerFromIntent(getIntent());
        RefreshUI(getCurrentCustomer());

    }

    public void openOrderManagement(View view) {
        Intent intent = new Intent(this, OrderManagementActivity.class);
        intent.putExtra("CartManager", cartManager);
        startActivity(intent);
    }

    public void openCustomerManagementNew(View view) {
        Intent intent = new Intent(this, CustomerManagementEditActivity.class);
        startActivityForResult(intent, NEW_CUSTOMER_REQUEST_CODE);
    }

    public void scanCustomerCard(View view) {
        String scannedID = cartManager.LocateCustomerWithCustomerCard();

        RefreshUI(getCurrentCustomer());
        if (scannedID.equals("ERR")) {
            myTextCurrentCustomer.setText("");
            myTextUniqueID.setText("QRCode Scan Error");
        }

    }


    public void openCustomerManagement(View view) {
       if ( getCurrentCustomer() == null){
           Intent intent = new Intent(this, CustomerManagementEditActivity.class);
           startActivityForResult(intent, NEW_CUSTOMER_REQUEST_CODE);
       }
       else {
           Intent intent = new Intent(this, CustomerManagementActivity.class);
           intent.putExtra("CartManager", cartManager);
           startActivityForResult(intent, VIEW_CUSTOMER_REQUEST_CODE);
       }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode)
        {
            case NEW_CUSTOMER_REQUEST_CODE:
            case VIEW_CUSTOMER_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK)
                {
                    ExtractCartManagerFromIntent(data);
                    RefreshUI(getCurrentCustomer());
                }
                break;
        }
    }


    private void RefreshUI(Customer current) {
        if ( current != null)
        {
            myTextUniqueID.setText(current.getUniqueID());
            myTextCurrentCustomer.setText(current.getName());
            btnCreateOrder.setEnabled(true);
            btnCustomerManagement.setEnabled(true);
        }
        else
        {
            btnCreateOrder.setEnabled(false);
            btnCustomerManagement.setEnabled(false);
        }

    }

    public void databaseMaintenanceClick(View view) {
        cartManager.databaseMaintenance();
        ShowMessage("Database Maintenance Completed");
        cartManager.setCurrentCustomer(null);
        RefreshUI(getCurrentCustomer());
        myTextUniqueID.setText("");
        myTextCurrentCustomer.setText("");
    }

}
