package com.example.infinity.all_student_details;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.infinity.R;
import com.example.infinity.date.ParseDate;
import com.example.infinity.date.currDate;
import com.example.infinity.ui.home.home_listitemClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class new_stdDetail extends Fragment {
    private int datearr=0;
    home_listitemClass pval;
    boolean status=true;

    public new_stdDetail() {
        super(R.layout.all_student_add_new_student);
    }

    Button date;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        date = (Button) view.findViewById(R.id.ed_doj);
        currDate.setCurrDate();// sets current date on static currdate class
        dateSelect();// for invoking datepickerdialog on click

        Button bb = (Button) view.findViewById(R.id.button);// submit info button
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View s) {
                EditText fees = (EditText) view.findViewById(R.id.ed_fees);
                String fee = fees.getText().toString();


                EditText nm = (EditText) view.findViewById(R.id.ed_name);
                String name = nm.getText().toString();
                EditText sb = (EditText) view.findViewById(R.id.ed_sub);
                String subject = sb.getText().toString();

                if (!fee.isEmpty() && datearr != 0 && !name.isEmpty()) {
                    student_details_class x = (new student_details_class(fee, datearr, new ParseDate(datearr).getdueDays(), 0, name, subject));
                    Log.i("new student detail", fee + "" + datearr + "" + name);
                    //writing in firebase
                    Bundle bundle = getArguments();
                    ArrayList arrayList = (new ArrayList<Integer>());
//
                    x.setArr(arrayList);

                    String aclass = bundle.getString("class");
                    FirebaseDatabase database = FirebaseDatabase.getInstance();

                    //whenever new student is added , the analytics node will be altered
                    setAnalytics(database,"class "+aclass,new home_listitemClass(1,0,Integer.parseInt(fee),"class "+aclass));
                    String key = database.getReference("class " + aclass).push().getKey();
                    x.setKey(key);
                    DatabaseReference reference = database.getReference("class " + aclass).child(key);
                    reference.setValue(x).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            //after data is writen this activity is finished
                            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_bottom_navigation_view);
                            navController.popBackStack();


                        }
                    });

                } else {
                    Toast.makeText(getContext(), "Empty Field", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
///to set the datepicker

    private void dateSelect() {
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        date.setText(i2 + "/" + i1 + "/" + i);
                        currDate.setCurrDate();
                        datearr = i2 * 1000000 + i1 * 10000 + i;
                        Log.w("date", datearr + "");

                    }
                }, currDate.mYear, currDate.mMonth, currDate.mDay);
                datePickerDialog.show();
            }
        });
    }
// method to set analytics , could be used with other class too
    public void setAnalytics(FirebaseDatabase database, String class_name, home_listitemClass obj)
    {
        DatabaseReference reference = database.getReference("Analytics").child(class_name);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.hasChildren())//checks for null
                {
                    Log.w("hii", "snapshot empty" );
                    reference.setValue(obj);
                    status=false;
                }
                else {

                    pval =snapshot.getValue(home_listitemClass.class);
                    Log.w("s", pval+"" );
                    //add previous firebase data with new generated data

                    if(status) {
                        reference.setValue(new home_listitemClass(obj.getNOS() + pval.getNOS(), obj.getNOPS() + pval.getNOPS(), obj.getTF() + pval.getTF(),pval.getClss()));
                    }
                    status=false;

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Error occured while posting data at analytics",Toast.LENGTH_LONG).show();
            }
        });
        status=true;

    }

}



