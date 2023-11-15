package es.unican.appriegospersonales.activities.smarthome;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

import es.unican.appriegospersonales.common.TextThumbSeekBar;
import es.unican.appriegospersonales.activities.main.MainView;
import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.common.adapters.RVActivosPerfilAdapter;
import es.unican.appriesgospersonales.R;

public class SmartHomeView extends Fragment implements ISmartHomeContract.View, MainView.RefreshableFragment {

    private ISmartHomeContract.Presenter presenter;
    private PieChart cvesPC;
    private RecyclerView devicesRV;
    private TextThumbSeekBar ecoRatingBar;
    private TextView pobreTV;
    private TextView justoTV;
    private TextView buenoTV;
    private TextView excelenteTV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SmartHomePresenter(this);
        presenter.init();
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_smart_home, container, false);
        cvesPC = layout.findViewById(R.id.cves_pc);
        devicesRV = layout.findViewById(R.id.devicesPerfil_rv);
        ecoRatingBar = layout.findViewById(R.id.seekBar);
        pobreTV = layout.findViewById(R.id.pobre_tv);
        justoTV = layout.findViewById(R.id.justo_tv);
        buenoTV = layout.findViewById(R.id.bueno_tv);
        excelenteTV = layout.findViewById(R.id.excelente_tv);

        devicesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        devicesRV.setAdapter(new RVActivosPerfilAdapter(getContext(), presenter.getActivosPerfilOrdenadosPorRiesgo(), getMyApplication()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                devicesRV.getContext(),
                DividerItemDecoration.VERTICAL);
        devicesRV.addItemDecoration(dividerItemDecoration);

        setUpPieChart();

        TextView numDevicesTV = layout.findViewById(R.id.numDevices_tv);
        numDevicesTV.setText(String.valueOf(presenter.getActivosPerfil().size()));
        TextView numVulnerabilitiesTV = layout.findViewById(R.id.numVulnerabilities_tv);
        numVulnerabilitiesTV.setText(String.valueOf(presenter.getVulnerabilidadesPerfil().size()));

        ecoRatingBar.setProgress(presenter.getEcoRatingHome());
        ecoRatingBar.setEnabled(false);
        destacaEcoBar();

        return layout;
    }

    private void destacaEcoBar() {
        int progress = ecoRatingBar.getProgress();

        Drawable thumbDrawable = ContextCompat.getDrawable(getContext(), R.drawable.shape_seek_bar_text_thumb);
        LayerDrawable layerDrawable = (LayerDrawable) thumbDrawable;
        GradientDrawable circleDrawable = (GradientDrawable) layerDrawable.getDrawable(1);

        if (progress < 25) {
            pobreTV.setTextColor(ContextCompat.getColor(getContext(), R.color.ecoPoor));
            pobreTV.setTypeface(Typeface.DEFAULT_BOLD);
            pobreTV.setTextSize(13f);
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.ecoPoor));
        } else if (progress < 50) {
            justoTV.setTextColor(ContextCompat.getColor(getContext(), R.color.ecoFair));
            justoTV.setTypeface(Typeface.DEFAULT_BOLD);
            justoTV.setTextSize(13f);
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.ecoFair));
        } else if (progress < 75) {
            buenoTV.setTextColor(ContextCompat.getColor(getContext(), R.color.ecoGood));
            buenoTV.setTypeface(Typeface.DEFAULT_BOLD);
            buenoTV.setTextSize(13f);
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.ecoGood));
        } else {
            excelenteTV.setTextColor(ContextCompat.getColor(getContext(), R.color.ecoExcelent));
            excelenteTV.setTypeface(Typeface.DEFAULT_BOLD);
            excelenteTV.setTextSize(13f);
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.ecoExcelent));
        }
        ecoRatingBar.setThumb(thumbDrawable);
    }

    private void setUpPieChart() {
        PieDataSet pieDataSet = new PieDataSet(presenter.getEntries(), "");
        pieDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                if (value == 0) {
                    return "";
                } else {
                    return String.valueOf((int) value);
                }
            }
        });
        PieData pieData = new PieData(pieDataSet);
        cvesPC.setData(pieData);
        pieDataSet.setColors(getColorEntries());
        pieDataSet.setSliceSpace(4f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(12f);

        cvesPC.getDescription().setEnabled(false);
        cvesPC.setDrawEntryLabels(false);
        cvesPC.setRotationEnabled(false);
        cvesPC.setTouchEnabled(false);
        cvesPC.setDragDecelerationFrictionCoef(0.95f);

        Legend legend = cvesPC.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        legend.setXEntrySpace(7f);// X axis spacing
        legend.setYEntrySpace(5f); // Y axis spacing
        legend.setYOffset(0f);  // Offset of the legend y
        legend.setXOffset(20f);  // Offset of legend x
        legend.setTextSize(12f);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setEnabled(true);
    }


    private ArrayList<Integer> getColorEntries() {
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.criticalV));
        colors.add(getResources().getColor(R.color.highV));
        colors.add(getResources().getColor(R.color.mediumV));
        colors.add(getResources().getColor(R.color.lowV));
        return colors;
    }

    @Override
    public void refreshData() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new SmartHomeView())
                .commit();
    }

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) requireActivity().getApplication();
    }
}
