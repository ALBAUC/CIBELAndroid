package es.unican.appriegospersonales.activities.apps.detail.tabs;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import es.unican.appriegospersonales.activities.apps.detail.RVCvesAdapter;
import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriesgospersonales.R;

public class TabCvesView extends Fragment implements ITabCvesContract.View {
    private TabCvesPresenter presenter;
    private PieChart cvePC;
    private Activo activo;

    public TabCvesView(Activo activo) {
        this.activo = activo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TabCvesPresenter(activo,this);
        presenter.init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_asset_detail_cves, container, false);

        RecyclerView assetCves_rv = layout.findViewById(R.id.assetDetail_cves_rv);
        cvePC = layout.findViewById(R.id.cve_pc);

        assetCves_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        assetCves_rv.setAdapter(new RVCvesAdapter(getContext(), presenter.getAssetCvesOrdenadorPorFecha()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(assetCves_rv.getContext(),
                DividerItemDecoration.VERTICAL);
        assetCves_rv.addItemDecoration(dividerItemDecoration);

        setUpPieChart();

        return layout;

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
        cvePC.setData(pieData);
        pieDataSet.setColors(getColorEntries());
        pieDataSet.setSliceSpace(4f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(12f);

        cvePC.getDescription().setEnabled(false);
        cvePC.setDrawEntryLabels(false);
        cvePC.setRotationEnabled(false);
        cvePC.setTouchEnabled(false);
        cvePC.setDragDecelerationFrictionCoef(0.95f);

        Legend legend = cvePC.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        legend.setXEntrySpace(7f);// X axis spacing
        legend.setYEntrySpace(5f); // Y axis spacing
        legend.setYOffset(-8f);  // Offset of the legend y
        legend.setXOffset(28f);  // Offset of legend x
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
    public MyApplication getMyApplication() {
        return (MyApplication) requireActivity().getApplication();
    }
}
