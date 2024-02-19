package com.example.infinity.profile_detail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infinity.R;
import com.example.infinity.all_student_details.MainFragment;
import com.example.infinity.all_student_details.student_details_class;
import com.example.infinity.date.ParseDate;
import com.example.infinity.date.currDate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class profile_main extends Fragment {

    DatePickerDialog datePickerDialog;
    View view;
    student_details_class value;
    String key;
    TextView dd;
    int datearr = 0;
    int due_day=0;
    Boolean status = false;
    ArrayList<Integer> arr;
    public profile_main(){
        super(R.layout.activity_profile_main);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.view=view;
        currDate.setCurrDate(); //to set curr date
        dd=(TextView)view.findViewById(R.id.Dm_profile);
        //to get extras from fragment (mainly key here) for fragment we use getArgument , for activitty we use getExtras
        Bundle bundle = getArguments();
        key = bundle.getString("key");
        arr = new ArrayList<Integer>();
        ListView listView = (ListView) view.findViewById(R.id.profilelistview);
        customadapterprofiledate adapter = new customadapterprofiledate(getContext(), arr);
        listView.setAdapter(adapter);

        DatabaseReference reference = MainFragment.reference.child(key);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                value = snapshot.getValue(student_details_class.class);
                TextView name = (TextView) view.findViewById(R.id.profilename);
                name.setText(value.getName());
                TextView fees = (TextView) view.findViewById(R.id.profilefees);
                fees.setText(value.getFees() + "");
                TextView sub = (TextView) view.findViewById(R.id.profilesubject);
                sub.setText(value.getSubject());
                TextView doj = (TextView) view.findViewById(R.id.profdoj);
                doj.setText(new ParseDate(value.getDoj()).getStringDate());
                arr.clear();
                if(value.getArr()!=null)
                    arr.addAll((value.getArr()));
              /*  else
                    arr.add(value.getDoj());*/


                checkStatus();
                setStatus();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

        feespaidbutton();
        dateselect();


    }




    void dateselect() {
        Button date = (Button) view.findViewById(R.id.profiledate);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (value.getStatus() == 1) {
                    toast("fees already paid");
                } else {
                    datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                            date.setText(i2 + "/"+ i1 + "/" + i);
                            datearr = i2 * 1000000 + i1 * 10000 + i;
                            Log.w("date", datearr + "");

                        }
                    }, currDate.mYear, currDate.mMonth, currDate.mDay);
                    datePickerDialog.show();
                }
            }


        });

    }

    public void feespaidbutton() {
        ((Button)view.findViewById(R.id.profilefeespaidbutton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (datearr == 0)
                    toast("date not selected");
               else if(value.getStatus()==1)
                    toast("fees already paid");
                else {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(datearr);
                    value.setArr(arrayList);
                    checkStatus();
                    MainFragment.reference.child(key).setValue(value);
                }
            }
        });


    }

    //custom method for toast
    private void toast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();

    }

    // method to set current date to these global var
    //now method shifted to currDate class
    private void checkStatus() {

        if (arr.size() != 0 && value.getStatus() != 1) {
            int last_payment = arr.get(arr.size() - 1);
            int R_day=new ParseDate(last_payment).getdueDays();
            if (R_day>30) {
                status = true;
                //value.setStatus(1);
               // value.setDm(R_day);
                due_day=R_day;
                dd.setText(due_day+"");
            }
        }
    }

    private void setStatus() {
        if (status) {
            MainFragment.reference.child(key).child("status").setValue(1);
            MainFragment.reference.child(key).child("dm").setValue(due_day);
            status = false;
        }
    }
}