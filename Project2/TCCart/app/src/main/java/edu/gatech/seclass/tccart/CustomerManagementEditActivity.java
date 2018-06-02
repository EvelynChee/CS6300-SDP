package edu.gatech.seclass.tccart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



import edu.gatech.seclass.tccart.db.Customer;
import edu.gatech.seclass.tccart.utility.Email;
import edu.gatech.seclass.tccart.utility.TCCartHelpers;
import edu.gatech.seclass.tccart.utility.UniqueID;

public class CustomerManagementEditActivity extends DBActivityBase {

    private EditText et_CustomerEmail;
    private TextView lbl_uniqueID;
    private EditText et_CustomerName;
    private boolean newCustomerActivity = false;
    private boolean currentCustomerModified = false;
    private String uniqueID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.onCreateBase(savedInstanceState);

        setContentView(R.layout.activity_customer_management_edit);
        TextView lbl_customerInformation = (TextView) findViewById(R.id.lbl_customerInformation);

        ExtractCartManagerFromIntent();
        lbl_uniqueID = (TextView) findViewById(R.id.lbl_UniqueID);
        et_CustomerEmail = (EditText) findViewById(R.id.et_Email);
        et_CustomerName = (EditText) findViewById(R.id.et_Name);


        if (getCurrentCustomer() != null) {
            lbl_customerInformation.setText("Edit Customer Information");
            newCustomerActivity = false;
            uniqueID = getCurrentCustomer().getUniqueID();
            RenderUI(getCurrentCustomer());

        } else {
            lbl_customerInformation.setText("New Customer Information");
            //New Customer..
            newCustomerActivity = true;
            UniqueID uid = UniqueID.GenerateID();
            uniqueID = uid.getID();
            lbl_uniqueID.setText(uniqueID);
        }

    }

    private void RenderUI(Customer current) {
        lbl_uniqueID.setText(current.getUniqueID());
        et_CustomerEmail.setText(current.getEmail());
        et_CustomerName.setText(current.getName());
    }


    protected void OnSave(View view) {

        String customerName = et_CustomerName.getText().toString();
        String customerEmail = et_CustomerEmail.getText().toString();

        customerEmail = customerEmail.trim();
        customerName = customerName.trim();

        if (customerName.isEmpty()) {
            ShowMessage("Please provide currentCustomer name!");
            return;
        }


        if (customerEmail.isEmpty()) {
            ShowMessage("Please provide an email address!");
            return;
        }

        if (!Email.IsValidEmail(customerEmail)) {
            ShowMessage("Please provide an valid email address!");
            return;
        }

        Customer customer = cartManager.AddNewOrEditCustomer(uniqueID, customerName, customerEmail);

        if (customer != null){
            currentCustomerModified = true;
            if (newCustomerActivity) {
                ShowMessage("New Customer Record Created");
                onBackPressed();
            }
            else {
                ShowMessage("Customer Record Updated!");
                onBackPressed();
            }

        } else {
            ShowMessage("There is another currentCustomer with same email address!");
        }

    }

    @Override
    public void onBackPressed() {
        Intent returnData = new Intent();
        returnData.putExtra("CartManager", cartManager);
        if (getIntent() != null) {
            if (currentCustomerModified)
                setResult(Activity.RESULT_OK, returnData);
            else {
                ShowMessage("Operation Canceled!");
                setResult(Activity.RESULT_CANCELED);
            }
        }

        finish();

    }



}