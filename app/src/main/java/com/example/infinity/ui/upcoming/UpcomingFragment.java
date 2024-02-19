package com.example.infinity.ui.upcoming;

import static android.content.ContentValues.TAG;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.infinity.R;
import com.example.infinity.ui.home.home_listadapter;
import com.example.infinity.ui.home.home_listitemClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UpcomingFragment extends Fragment {


public UpcomingFragment(){
    super(R.layout.classes_page);
}

  /*  public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG+"yyyy", "onCreateView: 2");

        View root = inflater.inflate(R.layout.fragment_home,null,false);


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
                if(snapshot.hasChildren()) {
                    Iterable<DataSnapshot> children = snapshot.getChildren();
                    for (DataSnapshot child : children) {
                        Log.d(TAG, "onDataChange: " + child.getValue(home_listitemClass.class));
                        arrayList.add(child.getValue(home_listitemClass.class));
                    }
                    listadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }*/




}