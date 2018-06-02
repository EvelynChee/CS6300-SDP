package edu.gatech.seclass.tccart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import edu.gatech.seclass.tccart.business.Item;
import edu.gatech.seclass.tccart.business.Order;
import edu.gatech.seclass.tccart.db.Customer;
import edu.gatech.seclass.tccart.db.Transaction;

public class OrderManagementActivity extends DBActivityBase {

    private Spinner mySpinnerItem;
    private TextView myTextCartTotal;
    private TextView myTextCreditDiscount;
    private TextView myTextVipDiscount;
    private TextView myTextOrderTotal;
    private EditText myEditTextUnitPrice;
    private ListView myListCart;

    private Calendar c1;

    private int cartRemovePosition;
    private ArrayAdapter<Item> adapter = null;
    private DecimalFormat df = new DecimalFormat("0.00");
    private Date todaysDate;

    double cartTotal = 0.00;
    double orderTotal = 0.00;
    double vipDiscount = 0.00;
    double creditDiscount = 0.00;
    Order order = new Order();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreateBase(savedInstanceState);

        setContentView(R.layout.activity_order_management);

        ExtractCartManagerFromIntent();

        c1 = GregorianCalendar.getInstance();

        mySpinnerItem = (Spinner) findViewById(R.id.spinnerItem);
        mySpinnerItem.setSelection(0);

        myTextCartTotal = (TextView) findViewById(R.id.textCartTotal);
        myTextCreditDiscount = (TextView) findViewById(R.id.textCreditDiscount);
        myTextVipDiscount = (TextView) findViewById(R.id.textVipDiscount);
        myTextOrderTotal = (TextView) findViewById(R.id.textOrderTotal);

        myEditTextUnitPrice = (EditText) findViewById(R.id.editTextUnitPrice);
        myEditTextUnitPrice.setText("2.50");

        myListCart = (ListView) findViewById(R.id.listCart);

        adapter = new ArrayAdapter<Item>(this,
                android.R.layout.simple_list_item_1, order.getOrderItems());

        myListCart.setAdapter(adapter);
        updateTotals();

        myListCart.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowMessage("Item Removed");
                cartRemovePosition = position;
                Item selectedItemToRemove= (Item) parent.getAdapter().getItem(position);
                order.RemoveItem(selectedItemToRemove);
                updateTotals();
                adapter.notifyDataSetChanged();
            }
        });

        mySpinnerItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    myEditTextUnitPrice.setText("2.50"); //tea default price
                } else {
                    myEditTextUnitPrice.setText("4.25"); //coffee default price
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    private void updateCartTotal() {
        cartTotal = 0;
        for (Item i : order.getOrderItems()) {
            cartTotal += i.getUnitPrice();
        }
        cartTotal = sanitizeDouble(cartTotal);
    }

    private double sanitizeDouble(double num) {
        //add input checking here also?
        return Double.valueOf(df.format(num));
    }

    private void updateTotals() {
        Customer currentCustomer = getCurrentCustomer();

        updateCartTotal();
        orderTotal = cartTotal;
        if(currentCustomer.getVIP()) {
            vipDiscount = sanitizeDouble(cartTotal * 0.10);
            orderTotal = cartTotal - vipDiscount;
        } else {
            vipDiscount = 0.00;
        }
        todaysDate = c1.getTime();
        creditDiscount = currentCustomer.getRewardBalance();
        if(creditDiscount > 3) {
            creditDiscount = 3.00; //this should never happen but just in case
        }
        if (creditDiscount > orderTotal) {
            creditDiscount = orderTotal;
        }


        if(currentCustomer.getRewardBalance() > 0.00 && todaysDate.after(currentCustomer.getRewardExpireDate())) {
            ShowMessage("Credit balance of $" + df.format(currentCustomer.getRewardBalance()) + " has expired.");
            currentCustomer.setRewardBalance(0.00);
            cartManager.saveCurrentCustomer();

        }

        orderTotal = orderTotal - creditDiscount;
        myTextCartTotal.setText("$" + df.format(cartTotal));
        myTextCreditDiscount.setText("-$" + df.format(creditDiscount));
        myTextVipDiscount.setText("-$" + df.format(vipDiscount));
        myTextOrderTotal.setText("$" + df.format(orderTotal));
    }

    public void addItem(View view) {
        String item = mySpinnerItem.getSelectedItem().toString();
        String itemPrice = myEditTextUnitPrice.getText().toString();
        double unitPrice = 0.0;
        try
        {
            unitPrice = sanitizeDouble(Double.valueOf(itemPrice) ); // add try and catch for invalid format
        }catch (Exception e){
            ShowMessage("Please check Unit Price and try again!");
            return;
        }


        order.AddItem(new Item(item, unitPrice)); //could add true false check to see if added beforing showing added
        updateTotals();
        //ShowMessage("Item Added");
        adapter.notifyDataSetChanged();
    }

    public void openPaymentManagement(View view) {
        //What to put for Long id?
        //Transaction(Long id, String customerID, java.util.Date TransactionDate, Double CartTotal, Double CreditDiscount, Double VIPDiscount, Double Total)
        if ( cartTotal > 0 ) {

            Transaction currentTransaction = cartManager.getCurrentCustomer().CreateNewTransaction();
            currentTransaction.setCartTotal(cartTotal);
            currentTransaction.setCreditDiscount(creditDiscount);
            currentTransaction.setVIPDiscount(vipDiscount);
            currentTransaction.setTotal(orderTotal);

            //new Transaction(null, currentCustomer.getUniqueID(), null, cartTotal, creditDiscount, vipDiscount, orderTotal);
            Intent intent = new Intent(this, PaymentManagementActivity.class);

            cartManager.setCurrentTransaction(currentTransaction);
            intent.putExtra("CartManager", cartManager);
            startActivity(intent);
        }
        else {
            ShowMessage("Please add some item into the Cart!");
        }
    }
}
