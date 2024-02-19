package com.example.infinity.ui.home;

import static android.content.ContentValues.TAG;

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
import com.example.infinity.databinding.FragmentHomeBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    public HomeFragment(){
super(R.layout.fragment_home);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: yoyoyoyoy");

        View root = inflater.inflate(R.layout.fragment_home,container,false);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        ArrayList<home_listitemClass> arrayList=new ArrayList<>();
        home_listadapter listadapter=new home_listadapter(getActivity(),arrayList);
        ListView listView=(ListView)view.findViewById(R.id.ListView_home);
        listView.setAdapter(listadapter);
        DatabaseReference analytics = database.getReference("Analytics");
        analytics.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                Iterable<DataSnapshot> children = snapshot.getChildren();
                for(DataSnapshot child:children)
                {
                    Log.d(TAG, "onDataChange: "+ child.getValue(home_listitemClass.class));
                    arrayList.add(child.getValue(home_listitemClass.class));
                }
                listadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}