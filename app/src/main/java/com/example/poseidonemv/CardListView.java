package com.example.poseidonemv;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class CardListView extends RecyclerView {

    public CardListView(@NonNull Context context) {
        super(context);
    }

    public CardListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CardListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
