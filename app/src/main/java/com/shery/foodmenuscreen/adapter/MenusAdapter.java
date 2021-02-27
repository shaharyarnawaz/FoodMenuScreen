package com.shery.foodmenuscreen.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shery.foodmenuscreen.R;
import com.shery.foodmenuscreen.model.MenuCategoryAndItems;

import java.util.ArrayList;

public class MenusAdapter extends RecyclerView.Adapter<MenusAdapter.ViewHolder> {
    ArrayList<MenuCategoryAndItems> list;
    //    TabClick click;
    MenuItemsAdapter adapter;
    Activity activity;

    public MenusAdapter(Activity activity, ArrayList<MenuCategoryAndItems> list/*, TabClick click*/) {
        this.activity = activity;
        this.list = list;
//        this.click = click;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_menu_title;
        RecyclerView rcv_menu_item;


        public ViewHolder(View view) {
            super(view);
            txt_menu_title = view.findViewById(R.id.txt_menu_title);
            rcv_menu_item = view.findViewById(R.id.rcv_menu_item);
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    click.onTabClicked(getAdapterPosition());
//                }
//            });
        }


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lay_item_menu, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.txt_menu_title.setText(list.get(position).getCategoryName());
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);

        viewHolder.rcv_menu_item.setLayoutManager(layoutManager);
        adapter = new MenuItemsAdapter(list.get(position).getMenuItemsLists());
        viewHolder.rcv_menu_item.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
