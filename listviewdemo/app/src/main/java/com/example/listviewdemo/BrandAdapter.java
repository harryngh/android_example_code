package com.example.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BrandAdapter extends BaseAdapter {

    ArrayList<Brand> brands;
    Context context;
    LayoutInflater inflater;

    public BrandAdapter(ArrayList<Brand> brands, Context context) {
        this.brands = brands;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return brands.size();
    }

    @Override
    public Object getItem(int i) {
        return brands.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder{
        TextView nameTV;
        TextView descTV;
        ImageView brandIMV;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(view==null){
            view = inflater.inflate(R.layout.row_brand_item,null);
            viewHolder=new ViewHolder();
            viewHolder.nameTV=view.findViewById(R.id.brandNameTV);
            viewHolder.descTV=view.findViewById((R.id.descTV));
            viewHolder.brandIMV=view.findViewById(R.id.brandIMV);
            view.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.nameTV.setText(brands.get(i).getName());
        viewHolder.descTV.setText(brands.get(i).getDescription());
        viewHolder.brandIMV.setImageDrawable(
                context.getResources().getDrawable(brands.get(i).getImage()));
        return view;
    }
}
