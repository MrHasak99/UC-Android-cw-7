package com.example.classwork5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter {

    ArrayList<Items> ItemsList = new ArrayList<>();
    Context context;

    public ItemAdapter(ArrayList<Items> itemsList, Context context) {
        ItemsList = itemsList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).textname.setText(ItemsList.get(position).getItemName());
        ((ViewHolder)holder).textprice.setText(ItemsList.get(position).getItemPrice() + " KD");
        Picasso.with(context).load(ItemsList.get(position).getItemImage()).into(((ViewHolder)holder).img);


        ((ViewHolder)holder).v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("item", ItemsList.get(position));
                context.startActivity(intent);

            }
        });

        ((ViewHolder)holder).delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Items removeditem = ItemsList.get(position);
                int x = position;

                AlertDialog.Builder alert = new AlertDialog.Builder(context)

                        .setTitle(("Attention"))

                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                ItemsList.remove(position);
                                notifyDataSetChanged();

                                Snackbar.make(context, view, "Item deleted", 5000)

                                        .setAction("Undo", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                ItemsList.add(position, removeditem);
                                                notifyDataSetChanged();

                                            }
                                        })

                                        .show();

                            }
                        })

                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();

                    }
                });

                alert.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return ItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img, delete;
        TextView textname, textprice;
        View v;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            img = v.findViewById(R.id.imageView);
            textname = v.findViewById(R.id.textViewName);
            textprice = v.findViewById(R.id.textViewPrice);
            delete = v.findViewById(R.id.imageViewDelete);
        }
    }
}
