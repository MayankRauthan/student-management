package com.example.infinity.class_activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;

import com.example.infinity.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class classes_adpater extends ArrayAdapter<class_members> {
    Context context;
    NavController navController;
    public classes_adpater(@NonNull Context context, @NonNull ArrayList<class_members> mem, NavController navController) {
        super(context, 0, mem);
        this.navController=navController;
        this.context=context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.item_layout_for_class_page,parent,false);
        else {

        }
        class_members m=getItem(position);
        ImageView imageView=(ImageView) convertView.findViewById(R.id.imagee);
        Picasso.get().load(m.getImg()).resize(200,200).onlyScaleDown().centerInside().into(imageView);

        TextView textView=(TextView) convertView.findViewById(R.id.class_no);
        textView.setText("class "+m.getClasss());
        (imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i=new Intent(getContext(), MainActivity.class);

                Bundle b=new Bundle();
                b.putString("class",""+m.getClasss());

                navController.navigate(R.id.action_navigation_dashboard_to_mainActivity,b);
               /* i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);*/
            }
        });
     return convertView;
    }
}
