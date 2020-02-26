package com.example.poseidonemv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {
    protected List<CardModel> listCard = new LinkedList();
    protected Context context;

    public CardAdapter(List<CardModel> listCard, Context context) {
        this.listCard = listCard;
        this.context = context;
    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item_layout, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, int position) {
        CardModel cardModel = listCard.get(position);
        holder.setDetails(cardModel);
    }

    @Override
    public int getItemCount() {
        return listCard.size();
    }


    protected class CardHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtCardNum, txtExpr;

        public CardHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tv_name);
            txtCardNum = itemView.findViewById(R.id.tv_number);
            txtExpr = itemView.findViewById(R.id.tv_expr);
        }

        public void setDetails(CardModel card){
            txtName.setText("Name : "+card.getName());
            txtCardNum.setText("Card Number : "+card.getName());
            txtExpr.setText("Expiration Date : "+card.getName());
        }
    }
}
