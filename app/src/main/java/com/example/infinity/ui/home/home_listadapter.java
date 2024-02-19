package com.example.infinity.ui.home;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.infinity.R;

import java.util.ArrayList;


public class home_listadapter extends ArrayAdapter<home_listitemClass> {
Activity activity;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.home_listitem, parent, false);
        home_listitemClass value=getItem(position);

        ((TextView) convertView.findViewById(R.id.textView)).setText(value.getClss());
        ((TextView) convertView.findViewById(R.id.textView3)).setText(value.getNOS()+"");
        ((TextView) convertView.findViewById(R.id.textView5)).setText(value.getNOPS()+"");
        ((TextView) convertView.findViewById(R.id.textView7)).setText(value.getNOS()-value.getNOPS()+"");
        ((TextView) convertView.findViewById(R.id.textView9)).setText(value.getTF()+"");
        return convertView;
    }

    public home_listadapter(@NonNull Activity activity, @NonNull ArrayList<home_listitemClass> objects) {
        super(activity, 0, objects);
        this.activity=activity;
    }
}
