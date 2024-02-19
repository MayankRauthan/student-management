package com.example.infinity.ui.unpaid;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.infinity.R;
import com.example.infinity.all_student_details.student_details_class;
import com.example.infinity.databinding.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UnpaidFragment extends Fragment {
    public UnpaidFragment(){}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


       View root=inflater.inflate(R.layout.fragment_unpaid,container,false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ListView listView=(ListView) view.findViewById(R.id.unpaid_listview);
        ArrayList<student_details_class> arr=new ArrayList<>();
        unpaid_listviewAdapter adapter=new unpaid_listviewAdapter(getContext(),arr);
        listView.setAdapter(adapter);
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        Log.d(TAG, "onViewCreated: inside fireonviewcreated unpaid");
        DatabaseReference reference = database.getReference();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Iterable<DataSnapshot> children = snapshot.getChildren();
                int c=1;
                for(DataSnapshot child:children){
                    if(c>1){
                        Iterable<DataSnapshot> children1 = child.getChildren();
                        for(DataSnapshot child2:children1){
                            Log.d(TAG, "onDataChange: "+child2.getValue(student_details_class.class));
                            student_details_class temp=child2.getValue(student_details_class.class);
                            if(temp.getStatus()==0)
                                arr.add(temp);
                        }
                    }
                    c++;
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}