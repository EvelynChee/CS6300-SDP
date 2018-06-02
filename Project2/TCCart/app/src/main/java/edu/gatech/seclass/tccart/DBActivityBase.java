package edu.gatech.seclass.tccart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import edu.gatech.seclass.tccart.business.CartManager;
import edu.gatech.seclass.tccart.db.Customer;
import edu.gatech.seclass.tccart.db.Transaction;

/**
 * Created by Bulent Coskun on 3/21/2016.
 */
abstract public class DBActivityBase extends AppCompatActivity {

    protected CartManager cartManager = null;

    protected void onCreateBase(Bundle savedInstanceState) {

        cartManager=new CartManager(this.getApplicationContext());
        cartManager.CheckInitialSetup();

    }



    protected  void ShowMessage(CharSequence text) {
        ShowMessage(text, Toast.LENGTH_SHORT);
    }

    protected  void ShowMessage(CharSequence text, int duration ){
        Context context = getApplicationContext();

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    protected void ExtractCartManagerFromIntent(){
        ExtractCartManagerFromIntent(this.getIntent());
    }

    protected void ExtractCartManagerFromIntent(Intent data) {
        CartManager cm =(CartManager) data.getSerializableExtra("CartManager");
        if( cm != null) {
            cartManager.setCurrentCustomer(cm.getCurrentCustomer());
            cartManager.setCurrentTransaction(cm.getCurrentTransaction());
        }
    }


    protected Customer getCurrentCustomer() {
        return cartManager.getCurrentCustomer();
    }

    protected Transaction getCurrentTransaction() {
        return cartManager.getCurrentTransaction();
    }
}
