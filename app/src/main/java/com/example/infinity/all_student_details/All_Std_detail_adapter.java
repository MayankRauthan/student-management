package com.example.infinity.all_student_details;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.infinity.R;
import com.example.infinity.date.ParseDate;
import com.example.infinity.profile_detail.profile_main;

import java.util.ArrayList;

public class All_Std_detail_adapter extends ArrayAdapter<student_details_class> {
private ArrayList<student_details_class> std2;
Activity a;
    public All_Std_detail_adapter(Activity mainActivity, ArrayList<student_details_class> std) {
        super(mainActivity, 0, std);
        a=mainActivity;
        std2=std;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View lstview = convertView;

        if (convertView == null) {
            lstview = LayoutInflater.from(getContext()).inflate(R.layout.all_student_detail, parent, false);
        }
        student_details_class std = getItem(position);

// fetching data from std and setting it to layout

        TextView f = (TextView) lstview.findViewById(R.id.fees);
        f.setText("" + std.getFees());
        TextView dm = (TextView) lstview.findViewById(R.id.da);
        dm.setText("" + std.getDm());
        TextView doj = (TextView) lstview.findViewById(R.id.doj);
        doj.setText("" + new ParseDate(std.getDoj()).getStringDate());//here int doj is parsed throught ParseDate class
        TextView name = (TextView) lstview.findViewById(R.id.name);
        name.setText(std.getName());
        ImageView img=(ImageView) lstview.findViewById(R.id.image);
        if (std.getStatus() == 1) {
                        img.setColorFilter(Color.parseColor("#00FF00"));
                    }
        else {
            img.setColorFilter(Color.parseColor("#FF0000"));
        }
        ((LinearLayout)lstview.findViewById(R.id.linearLayout_stddetail)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent i=new Intent(getContext(), profile_main.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                i.putExtra("key",std.getKey());

                a.startActivity(i);*/

                // providing which class to bundle ,and then to next fragment
                Bundle b=new Bundle();
                b.putString("key",std.getKey());
                NavController navController= Navigation.findNavController(a,R.id.nav_host_fragment_activity_bottom_navigation_view);
                navController.navigate(R.id.action_mainActivity_to_profile_main,b);

            }


        });





        return lstview;

    }

}
