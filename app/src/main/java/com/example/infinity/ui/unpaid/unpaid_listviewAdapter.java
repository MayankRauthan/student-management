package com.example.infinity.ui.unpaid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.infinity.R;
import com.example.infinity.all_student_details.student_details_class;
import com.example.infinity.ui.home.home_listitemClass;

import java.util.ArrayList;
import java.util.List;


public class unpaid_listviewAdapter extends ArrayAdapter<student_details_class> {

    public unpaid_listviewAdapter(@NonNull Context context, @NonNull ArrayList<student_details_class> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.unpaid_listitems, parent, false);
       student_details_class value=getItem(position);
        ((TextView)convertView.findViewById(R.id.nameTextView)).setText(value.getName());
        ((TextView)convertView.findViewById(R.id.classTextView)).setText(value.getClass()+"");
        ((TextView)convertView.findViewById(R.id.feesTextView)).setText(value.getFees());
        return convertView;

    }
}
