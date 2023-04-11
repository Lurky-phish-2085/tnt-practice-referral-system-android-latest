package xyz.lurkyphish2085.tntpracticereferralsystem;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.Transaction;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    List<Transaction> transactionList = new ArrayList<>();

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_transaction_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        Transaction currentItem = transactionList.get(position);
        holder.setData(String.valueOf(currentItem.getAmount()), currentItem.getSource(), currentItem.getDate());
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    void setTransactionList(List<Transaction> list) {
        transactionList = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView transactionAmount, transactionSource, transactionDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionAmount = itemView.findViewById(R.id.transaction_amount_tv);
            transactionSource = itemView.findViewById(R.id.transaction_source_tv);
            transactionDate = itemView.findViewById(R.id.transaction_date_tv);
        }

        void setData(String amount, String source, String date) {
            transactionAmount.setText(amount);
            transactionSource.setText(source);
            transactionDate.setText(date);
        }
    }
}
