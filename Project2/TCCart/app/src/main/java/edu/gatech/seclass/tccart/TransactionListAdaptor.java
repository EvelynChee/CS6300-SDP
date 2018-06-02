package edu.gatech.seclass.tccart;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.gatech.seclass.tccart.db.Transaction;

/**
 * Created by changliu on 2016/3/22.
 */
public class TransactionListAdaptor extends BaseAdapter {

    private Context context;
    private List<Transaction> transactionList;

    public TransactionListAdaptor(Context context, List<Transaction> transactionList ) {
        super();
        this.context=context;
        this.transactionList=transactionList;
    }

    @Override
    public int getCount() {
        return transactionList.size();
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //need to construct a view object to display the data in the List item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Transaction transactionItem = transactionList.get(position);
        DecimalFormat format = new DecimalFormat("0.00");



        LayoutInflater inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup group= (ViewGroup)inflater.inflate(R.layout.transaction_item, null);

        if (position % 2 == 0 )
        {
            group.setBackgroundColor(Color.LTGRAY);
        }

        TextView textView1=(TextView) group.findViewById(R.id.textView1);
            Date date = transactionItem.getTransactionDate();
            String TransactionDate=DateFormat.getDateInstance().format(date);
            textView1.setText(TransactionDate);
        TextView textView2=(TextView) group.findViewById(R.id.textView2);
            String CartTotal="$"+transactionItem.getCartTotal();
            textView2.setText(CartTotal);
        TextView textView3=(TextView) group.findViewById(R.id.textView3);
            String CreditDiscount="-$"+ String.valueOf(transactionItem.getCreditDiscount());
            textView3.setText(CreditDiscount);
        TextView textView4=(TextView) group.findViewById(R.id.textView4);
            String VIPdiscount="-$"+transactionItem.getVIPDiscount();
            textView4.setText(VIPdiscount);
        TextView textView5=(TextView) group.findViewById(R.id.textView5);
            String Total="$"+transactionItem.getTotal();
            textView5.setText(Total);

        return group;
    }


}
