package com.example.kwon.dietmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class FoodCalorieFragment extends Fragment {
    int numvals = 5;
    public static final int NAME = 0;
    public static final int BRANCH = 1;
    public static final int EMAIL = 2;
    public static final int REGISTRATION = 3;
    public static final int ADMISSION = 4;

    String[] details = new String[5];
    String[] detailVals = new String[5]; // Get the values from JSON here
    public FoodCalorieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null)
        {
            return null;
        }

        details[NAME] = "Name";
        details[BRANCH] = "Branch";
        details[EMAIL] = "Email";
        details[REGISTRATION] = "Registration No.";
        details[ADMISSION] = "Admission No. ";

        detailVals[NAME] = "Some Name";
        detailVals[BRANCH] = "Computer Science";
        detailVals[EMAIL] = "some_email@email.com";
        detailVals[REGISTRATION] = "11101212";
        detailVals[ADMISSION] = "1345645";

        // xml에 명시한 위젯이 안드로이드 메모리에 생성되는 과정을 인플레이션이라고 한다.
        View view = inflater.inflate(R.layout.fragment_food_calorie, container, false);

        TableLayout tl = (TableLayout)view.findViewById(R.id.TableLayout);

        for (int i = 0; i < numvals; i++)
        {
            // Make TR
            TableRow tr = new TableRow(getActivity());
            tr.setId(100 + i);
            tr.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

            // Make TV to hold the details
            TextView detailstv = new TextView(getActivity());
            detailstv.setTextSize(20);
            detailstv.setId(200 + i);
            detailstv.setText(details[i]);
            tr.addView(detailstv);

            // Make TV to hold the detailvals

            TextView valstv = new TextView(getActivity());
            valstv.setTextSize(20);
            valstv.setId(300 + i);
            valstv.setText(detailVals[i]);
            tr.addView(valstv);

            tl.addView(tr, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        }


        return view;
        //return inflater.inflate(R.layout.fragment_food_calorie, container, false);
    }
}
