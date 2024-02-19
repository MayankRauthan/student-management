package com.example.infinity.all_student_details;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.infinity.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private View view;
    protected ArrayList<student_details_class> std = new ArrayList<>();
    protected All_Std_detail_adapter adapter;
     public static DatabaseReference reference;
    int count=0;

    ShimmerFrameLayout shimmerFrameLayout;
    public MainFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.coordinate_layout_student_detail, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.view=view;
       /* if (savedInstanceState != null) {
            // Retrieve any previously saved data from the Bundle
            String savedText = savedInstanceState.getString("key_for_saved_text");
            Log.w("see the saved",savedText);
        }*/
        Bundle bundle=getArguments();
        assert bundle != null;
        String aclass = bundle.getString("class");
        shimmerFrameLayout=(ShimmerFrameLayout) view.findViewById(R.id.shimmer_layout);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmer();
        Log.w("class no","the class is"+aclass);

        setAdapter();

        FirebaseDatabase database=FirebaseDatabase.getInstance();

        reference=database.getReference("class "+aclass);
        readFromDatabase();
        if(shimmerFrameLayout.isShimmerVisible())
        {
            shimmerFrameLayout.stopShimmer();
            shimmerFrameLayout.setVisibility(View.GONE);
        }

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.floating);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this, new_stdDetail.class);
                Bundle i=new Bundle();
                i.putString("class",aclass);
                i.putInt("count",count);

                NavController navController= Navigation.findNavController(getActivity(),R.id.nav_host_fragment_activity_bottom_navigation_view);

                navController.navigate(R.id.action_mainActivity_to_new_stdDetail,i);
//                startActivityForResult(i,1);


            }
        });
    }

    private void updateDueDate(FirebaseDatabase database) {

    }

    //reading from database
    private void readFromDatabase(){



        shimmerFrameLayout.startShimmer();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                std.clear();
                if(snapshot==null)
                    return;
                Iterable<DataSnapshot> children = snapshot.getChildren();
                for(DataSnapshot s:children)
                {
//                    temp++;
//                    if(temp<count) {
//                        continue;
//                    }
                    std.add(s.getValue(student_details_class.class));
                    count++;
                }
                shimmerFrameLayout.stopShimmer();
               // shimmerFrameLayout.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Something wrong",Toast.LENGTH_LONG).show();

            }
        });


    }
    void setAdapter() {
        ListView lst = (ListView) (view.findViewById(R.id.list_view));

        adapter = new All_Std_detail_adapter(getActivity(), std);
        lst.setAdapter(adapter);

    }

  /*  @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.dashboardItem);
    }*/
}