package com.shery.foodmenuscreen.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.shery.foodmenuscreen.R;
import com.shery.foodmenuscreen.model.MenuItems;

import java.util.ArrayList;

public class MenuItemsAdapter extends RecyclerView.Adapter<MenuItemsAdapter.ViewHolder> {
    ArrayList<MenuItems> list;

    public MenuItemsAdapter(ArrayList<MenuItems> list) {
        this.list = list;
    }


//    public void setList(ArrayList<MenuItems> list) {
//        this.list = list;
//        notifyDataSetChanged();
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dish_name;
        TextView dish_description;
        TextView dish_price;


        public ViewHolder(View view) {
            super(view);

            dish_name = view.findViewById(R.id.dish_name);
            dish_description = view.findViewById(R.id.dish_description);
            dish_price = view.findViewById(R.id.dish_price);
        }


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_items, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.dish_name.setText("Name : " + list.get(position).getName());

        if (list.get(position).getDescription() != null && !list.get(position).getDescription().equalsIgnoreCase(""))
            viewHolder.dish_description.setText(list.get(position).getDescription());
        else
            viewHolder.dish_description.setText("Description : N/A");

        viewHolder.dish_price.setText("Price : " + list.get(position).getPrice());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }


}
