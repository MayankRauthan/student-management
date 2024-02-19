package com.example.infinity.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.infinity.R;
import com.example.infinity.class_activity.class_members;
import com.example.infinity.class_activity.classes_adpater;
import com.example.infinity.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.classes_page,container,false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ArrayList<class_members> arr=new ArrayList<>();
//        Thread t=new Thread(new Runnable() {
//            @Override
//            public void run() {
                arr.add(new class_members(R.drawable.calculator,6));
                arr.add(new class_members(R.drawable.pencil,7));
                arr.add(new class_members(R.drawable.protactor,8));
                arr.add(new class_members(R.drawable.geography,9));
                arr.add(new class_members(R.drawable.hitler,10));
                arr.add(new class_members(R.drawable.newton,11));
                arr.add(new class_members(R.drawable.einstein,12));
//            }
//        });
//        t.start();
        NavController navController= Navigation.findNavController(getActivity(),R.id.nav_host_fragment_activity_bottom_navigation_view);
        GridView viewById = (GridView) view.findViewById(R.id.grid_views);

        classes_adpater adpater=new classes_adpater(getContext(),arr,navController);
        viewById.setAdapter(adpater);

    }

}