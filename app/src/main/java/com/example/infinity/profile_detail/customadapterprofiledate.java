package com.example.infinity.profile_detail;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.infinity.R;

import java.util.ArrayList;

public class customadapterprofiledate extends ArrayAdapter<Integer> {
    public customadapterprofiledate(@NonNull Context context, ArrayList<Integer> arr) {
        super(context,0, arr);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.profile_fees_adapter, parent, false);
            Log.w("wakeup", position+" "+convertView+"  "+parent );
        }
        int a=getItem(position);
        String date= parseDate(a);
        Log.w("inside adapter",a+"");
        ((TextView) convertView.findViewById(R.id.fees_history)).setText(date+"");
        return convertView;

    }
    private String parseDate(int date) {

        return date/1000000+"/"+(date/10000)%100+"/"+date%10000;
    }
}
