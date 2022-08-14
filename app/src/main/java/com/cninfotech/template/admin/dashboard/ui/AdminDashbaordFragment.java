package com.cninfotech.template.admin.dashboard.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.cninfotech.template.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class AdminDashbaordFragment extends Fragment {
    public AdminDashbaordFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_dashboard, container, false);
        PieChart chart = view.findViewById(R.id.chart1);
        ArrayList<PieEntry> yvalues = new ArrayList<>();
        yvalues.add(new PieEntry(20f, "Baggy Pants"));
        yvalues.add(new PieEntry(50f, "Demin Pants"));
        yvalues.add(new PieEntry(30f, "Nike Boots"));

        PieDataSet dataSet = new PieDataSet(yvalues, "Monthly Sales");

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(20f);
        data.setValueTextColor(Color.WHITE);
        chart.animateXY(1,1);
        chart.setData(data);
        chart.setDrawHoleEnabled(true);
        chart.setTransparentCircleRadius(5f);
        chart.setHoleRadius(10f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);


        return view;
    }

}
