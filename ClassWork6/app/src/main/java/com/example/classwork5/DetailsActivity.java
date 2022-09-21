package com.example.classwork5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView nametext = findViewById(R.id.textViewNameD);
        TextView pricetext = findViewById(R.id.textViewPriceD);
        ImageView img = findViewById(R.id.imageViewDetails);

        Bundle bundle = getIntent().getExtras();

        Items sentitem = (Items) bundle.getSerializable("item");

        nametext.setText(sentitem.getItemName());
        pricetext.setText(sentitem.getItemPrice() + " KD");

        Picasso.with(this).load(sentitem.getItemImage()).into(img);


    }
}