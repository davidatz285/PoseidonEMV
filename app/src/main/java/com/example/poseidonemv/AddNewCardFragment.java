package com.example.poseidonemv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.poseidonemv.ui.main.PageViewModel;
import com.example.poseidonemv.ui.main.PlaceholderFragment;

public class AddNewCardFragment extends Fragment {
    public static AddNewCardFragment newInstance(int index) {
        AddNewCardFragment fragment = new AddNewCardFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_new_card, container, false);
        return root;
    }
}
