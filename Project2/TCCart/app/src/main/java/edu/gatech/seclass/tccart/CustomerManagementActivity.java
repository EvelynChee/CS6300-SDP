package edu.gatech.seclass.tccart;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

import edu.gatech.seclass.tccart.business.CartManager;
import edu.gatech.seclass.tccart.utility.TCCartHelpers;
import edu.gatech.seclass.tccart.db.Customer;

public class CustomerManagementActivity extends DBActivityBase {

    private TextView lbl_UniqueID;
    private TextView lbl_CustomerEmail;
    private TextView lbl_CustomerName;
    private TextView lbl_RewardBalance;
    private TextView lbl_RewardExpireDate;
    private TextView lbl_SpendBalance;
    private TextView lbl_VIPStatus;
    private static final int EditCurrentCustomerResultCode = 1000;
    private Intent currentIntent;
    private boolean currentCustomerModified = false;
    private TextView lbl_VIPBegins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.onCreateBase(savedInstanceState);

        setContentView(R.layout.activity_customer_management);

        lbl_UniqueID = (TextView)this.findViewById(R.id.lbl_UniqueID);
        lbl_CustomerEmail = (TextView)this.findViewById(R.id.lbl_CustomerEmail);
        lbl_CustomerName = (TextView)this.findViewById(R.id.lbl_CustomerName);
        lbl_RewardBalance = (TextView) this.findViewById(R.id.lbl_RewardBalance);
        lbl_RewardExpireDate = (TextView)this.findViewById(R.id.lbl_RewardExpireDate);
        lbl_SpendBalance = (TextView)this.findViewById(R.id.lbl_SpendBalance);
        lbl_VIPStatus = (TextView) this.findViewById(R.id.lbl_VIPStatus);
        lbl_VIPBegins = (TextView) this.findViewById(R.id.lbl_VIPBegins);

        currentIntent = getIntent();
        cartManager = (CartManager) currentIntent.getSerializableExtra("CartManager");

        if ( cartManager.getCurrentCustomer() != null) {
            RefreshUI(cartManager.getCurrentCustomer());
        }
        else {
            ClearUI();
        }

    }

    private void ClearUI() {
        lbl_UniqueID.setText("-");
        lbl_CustomerName.setText("-");
        lbl_RewardBalance.setText("-");

        lbl_RewardExpireDate.setText("-");
        lbl_SpendBalance.setText("-");
        lbl_VIPStatus.setText("-");
        lbl_VIPBegins.setText("-");
    }

    private void RefreshUI(Customer customer) {
        lbl_UniqueID.setText(customer.getUniqueID());
        lbl_CustomerName.setText(customer.getName());
        lbl_CustomerEmail.setText(customer.getEmail());

        String rewardBalance = MoneyFormatter("$0.00", customer.getRewardBalance());
        lbl_RewardBalance.setText(rewardBalance);
        String spendBalance = MoneyFormatter("$0.00", customer.getSpendBalance());
        lbl_SpendBalance.setText(spendBalance);

        lbl_RewardExpireDate.setText(TCCartHelpers.FormatDate(customer.getRewardExpireDate()) );
        Boolean isVIP = customer.getVIP();
        lbl_VIPStatus.setText(FormatVIPStatus(isVIP));
        lbl_VIPBegins.setText(TCCartHelpers.FormatDate(customer.getVIPDiscountBegins()));

    }

    private String FormatVIPStatus(Boolean value){

        if ( value == null) return "No";

        if (value) return "Yes";

        return "No";
    }


    private String MoneyFormatter(String pattern, Double value) {

        if (value == null) value = 0.0;

        DecimalFormat myFormatter = new DecimalFormat(pattern);
        return myFormatter.format(value);
    }


    public void OnEdit(View view){
        Intent editIntent = new Intent(this, CustomerManagementEditActivity.class);
        editIntent.putExtra("CartManager", cartManager);
        startActivityForResult(editIntent, EditCurrentCustomerResultCode);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode)
        {
            case EditCurrentCustomerResultCode :
                if (resultCode == Activity.RESULT_OK)
                {
                    cartManager = (CartManager) data.getSerializableExtra("CartManager");
                    currentCustomerModified = true;
                    RefreshUI(cartManager.getCurrentCustomer());
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {

        Intent returnData = new Intent();
        returnData.putExtra("CartManager", cartManager);

        if (currentIntent != null){
            if (currentCustomerModified)
                setResult(Activity.RESULT_OK,returnData);
            else {
                setResult(Activity.RESULT_CANCELED);
            }
        }

        finish();
    }

    public void OnShowTransactions(View view){
        Intent transactionHistoryIntent = new Intent(this, TransactionHistoryActivity.class);
        transactionHistoryIntent.putExtra("CartManager", cartManager);
        startActivity(transactionHistoryIntent);
    }

    public void OnPrintCard(View view){

        boolean printed = cartManager.PrintCustomerCard();

        if ( printed ){
            ShowMessage("Card Printed.");
        }else
        {
            ShowMessage("Error Occurred, Please try again!");
        }
    }
}
