package com.example.ligakasuskorupsi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide; // BARIS INI HARUS ADA DAN BENAR

import com.example.ligakasuskorupsi.models.Kasus;

import java.util.List;

public class KasusAdapter extends BaseAdapter {

    private final Context context;
    private final List<Kasus> kasusList;

    public KasusAdapter(Context context, List<Kasus> kasusList) {
        this.context = context;
        this.kasusList = kasusList;
    }

    @Override
    public int getCount() {
        return kasusList.size();
    }

    @Override
    public Object getItem(int position) {
        return kasusList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return kasusList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        ImageView itemIcon = convertView.findViewById(R.id.itemIcon);
        TextView itemName = convertView.findViewById(R.id.itemName);

        Kasus kasus = kasusList.get(position);

        itemName.setText(kasus.getNama());

        if (kasus.getFotoPath() != null && !kasus.getFotoPath().isEmpty()) {
            Glide.with(context).load(kasus.getFotoPath()).into(itemIcon);
        } else {
            itemIcon.setImageResource(R.mipmap.ic_launcher);
        }

        return convertView;
    }
}
