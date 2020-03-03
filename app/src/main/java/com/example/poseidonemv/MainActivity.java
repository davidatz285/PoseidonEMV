package com.example.poseidonemv;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.poseidonemv.QrCodeGenerator.Encoder;
import com.example.poseidonemv.QrCodeGenerator.QRCodeGenerator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.poseidonemv.ui.main.SectionsPagerAdapter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.fm = getSupportFragmentManager();
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);
//        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        List<CardModel> list = new LinkedList<>();
//        list.add(new CardModel("Dvid","0896545621789653","78/78"));
//        // specify an adapter (see also next example)
//        mAdapter = new CardAdapter(null,this);
//        recyclerView.setAdapter(mAdapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        //initiateQrCode("1234567890123458", "1912","12345" ,"123456");

    }
    public void initiateQrCode(String creditCardNumber,String MMYY,String CVV,String pin){
        Dialog myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.qr_code_layout);
        ImageView im = myDialog.findViewById(R.id.QR_im);
        Encoder encoder = new Encoder();
        String result = encoder.encryptBase64(creditCardNumber, MMYY, CVV, pin);
        Log.d("result",result);
        Bitmap bitmap = QRCodeGenerator
                        .newInstance(this)
                        .setContent(result)
                        .setErrorCorrectionLevel(ErrorCorrectionLevel.Q)
                        .setMargin(2)
                        .generate();
        im.setImageBitmap(bitmap);
        myDialog.show();
    }


}