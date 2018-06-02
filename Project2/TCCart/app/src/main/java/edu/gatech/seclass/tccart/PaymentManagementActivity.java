package edu.gatech.seclass.tccart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import edu.gatech.seclass.tccart.business.CreditCard;
import edu.gatech.seclass.tccart.db.Customer;
import edu.gatech.seclass.tccart.db.Transaction;

import java.util.Calendar;
import java.util.Date;

import edu.gatech.seclass.services.*;
import edu.gatech.seclass.tccart.utility.CreditCardProcessor;
import edu.gatech.seclass.tccart.utility.CreditCardScanner;

public class PaymentManagementActivity extends DBActivityBase {

    private TextView TextCreditCard;
    private TextView TextDate;
    private TextView TextCVV;
    private Button myButtonPmtProcess;
    private Button myButtonSwipeCard;
    private Transaction.EmailResult emailResult;
    private Button btnSendNotification;
    private TextView myTextTxnTotal;
    private CreditCard creditCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreateBase(savedInstanceState);

        setContentView(R.layout.activity_payment_management);

        ExtractCartManagerFromIntent();

        myTextTxnTotal = (TextView) findViewById(R.id.textTxnTotal);
        TextCreditCard = (TextView) findViewById(R.id.textCreditCardNumber);
        TextDate = (TextView) findViewById(R.id.textDate);
        TextCVV = (TextView) findViewById(R.id.textCVV);
        myButtonPmtProcess = (Button) findViewById(R.id.buttonPmtProcess);
        myButtonSwipeCard = (Button) findViewById(R.id.buttonSwipeCard);
        btnSendNotification = (Button) findViewById(R.id.buttonSendNotification);

        RenderUI();

    }

    private void RenderUI() {

        Transaction currentTransaction = getCurrentTransaction();

        double orderTotal = currentTransaction.getTotal();
        DecimalFormat df = new DecimalFormat("0.00");
        myTextTxnTotal.setText(df.format(orderTotal));


        if ( orderTotal > 0 ) {
            myButtonPmtProcess.setEnabled(false);
            myButtonSwipeCard.setEnabled(true);
        }
        else {
            myButtonPmtProcess.setEnabled(true);
            myButtonSwipeCard.setEnabled(false);
        }

        btnSendNotification.setVisibility(View.INVISIBLE);
    }

    public void swipeCard(View view) {

        creditCard = CreditCardScanner.ReadCreditCard();


        if (creditCard == null ) {
            TextCreditCard.setText("CC Swipe Error");
            TextDate.setText("-");
            TextCVV.setText("-");
            myButtonPmtProcess.setEnabled(false);
            return;
        }

        myButtonPmtProcess.setEnabled(true);
        TextCreditCard.setText(creditCard.AccountNumber);

        TextDate.setText(creditCard.ExpirationDate);
        TextCVV.setText(creditCard.SecurityCode);
    }

    public void pmtProcess(View view) {

        Transaction currentTransaction = getCurrentTransaction();
        double orderTotal = currentTransaction.getTotal();
        double cartTotal = currentTransaction.getCartTotal();

        if ( orderTotal > 0 ) {
            if (creditCard == null) {
                return;
            }
        }

        if ( orderTotal > 0 && cartTotal > 0) {

            if (CreditCardProcessor.ProcessPurchase(currentTransaction, creditCard)) {
                ShowMessage("Payment Processed");
                addTransaction();
                updateCustomerStatusAndSendEmail();
                myButtonSwipeCard.setEnabled(false);
                myButtonPmtProcess.setEnabled(false);
            } else {
                ShowMessage("Payment Processing Failed");
            }
        }
        else
        {
            addTransaction();
            ShowMessage("Customer Transaction Completed!");
            updateCustomerStatusAndSendEmail();
        }

    }

    public boolean addTransaction() {

        Transaction currentTransaction = cartManager.getCurrentTransaction();
        Calendar calendar = Calendar.getInstance();
        currentTransaction.setTransactionDate(calendar.getTime());
        cartManager.saveCurrentTransaction();
        return true;
    }

    public boolean updateCustomerStatusAndSendEmail() {

        Transaction currentTransaction = cartManager.getCurrentTransaction();
        emailResult = currentTransaction.calculateBalanceAndSentEmail();
        cartManager.saveCurrentTransaction();
        cartManager.saveCurrentCustomer();
        checkEmailSentStep();
        return true;
    }


    public void SendNotification(View view){
        //we already updated all balance related information so we just need to keep try to sent an email...
        emailResult = getCurrentTransaction().sendEmails();
        checkEmailSentStep();
    }

    private void checkEmailSentStep(){

        if (emailResult.SendSuccessfully){
            ShowMessage("Email sent: " + emailResult.EmailMessage);
            Intent intent = new Intent(this, MainMenuActivity.class);
            intent.putExtra("CartManager", cartManager);
            startActivity(intent);
        } else {
            ShowMessage("Email failed to send.");
            btnSendNotification.setVisibility(View.VISIBLE);
            myButtonPmtProcess.setEnabled(false);
            myButtonSwipeCard.setEnabled(false);
        }

    }
}