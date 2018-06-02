package edu.gatech.seclass.tccart;


import android.os.Bundle;
import android.widget.ListView;
import java.util.List;
import edu.gatech.seclass.tccart.db.Transaction;

public class TransactionHistoryActivity extends DBActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreateBase(savedInstanceState);

        setContentView(R.layout.activity_transaction_history);

        ExtractCartManagerFromIntent(getIntent());
        List<Transaction> transactionList = cartManager.getCurrentCustomerTransactions();
        
        ListView listView = (ListView) findViewById(R.id.listView);

        TransactionListAdaptor adapter = new TransactionListAdaptor(this, transactionList);
        listView.setAdapter(adapter);

    }


}
