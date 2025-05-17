package com.example.ligakasuskorupsi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class KasusAdapter extends BaseAdapter {

    private final Context context;
    private final List<String> kasusList;

    public KasusAdapter(Context context, List<String> kasusList) {
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        ImageView itemIcon = convertView.findViewById(R.id.itemIcon);
        TextView itemName = convertView.findViewById(R.id.itemName);

        // Set nama kasus secara custom
        String[] namaKasus = {
                "Kasus PT. Pertamina",
                "Kasus PT. Timah",
                "Kasus PT. BLBI",
                "Kasus PT. Duta Palma Group",
                "Kasus PT. TPPI",
                "Kasus PT. Asabri",
                "Kasus PT. Jiwasraya",
                "Kasus To be continue",
                "Kasus To be continue",
                "Kasus To be continue"
        };

        itemName.setText(namaKasus[position]);

        // Set icon kasus berdasarkan nomor
        int iconResId = context.getResources().getIdentifier(
                "ic_kasus_" + (position + 1), "mipmap", context.getPackageName());
        itemIcon.setImageResource(iconResId);

        return convertView;
    }
}