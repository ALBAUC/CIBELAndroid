package es.unican.appriegospersonales.activities.apps.detail.tabs;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import es.unican.appriegospersonales.model.Activo;
import es.unican.appriesgospersonales.R;

public class TabSostenibilidadView extends Fragment implements ITabSostenibilidadContract.View {

    private TabSostenibilidadPresenter presenter;
    private Activo activo;

    public TabSostenibilidadView(Activo activo) {
        this.activo = activo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TabSostenibilidadPresenter(activo, this);
        presenter.init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_asset_detail_eco, container, false);

        // Link a los elementos de la vista
        TextView durabilidadTV = layout.findViewById(R.id.durabilidad_tv);
        CustomSeekBar durabilidadSB = layout.findViewById(R.id.durabilidad_hbc);

        TextView reparabilidadTV = layout.findViewById(R.id.reparabilidad_tv);
        CustomSeekBar reparabilidadSB = layout.findViewById(R.id.reparabilidad_hbc);

        TextView reciclabilidadTV = layout.findViewById(R.id.reciclabilidad_tv);
        CustomSeekBar reciclabilidadSB = layout.findViewById(R.id.reciclabilidad_hbc);

        TextView efClimaticaTV = layout.findViewById(R.id.efClimatica_tv);
        CustomSeekBar efClimaticaSB = layout.findViewById(R.id.efClimatica_hbc);

        TextView efRecursosTV = layout.findViewById(R.id.efRecursos_tv);
        CustomSeekBar efRecursosSB = layout.findViewById(R.id.efRecursos_hbc);

        // Asignar valores
        durabilidadTV.setText(String.valueOf(presenter.getDurabilidad()));
        reparabilidadTV.setText(String.valueOf(presenter.getReparabilidad()));
        reciclabilidadTV.setText(String.valueOf(presenter.getReciclabilidad()));
        efClimaticaTV.setText(String.valueOf(presenter.getEfClimatica()));
        efRecursosTV.setText(String.valueOf(presenter.getEfRecursos()));

        configurarSeekBar(durabilidadSB, presenter.getDurabilidad());
        configurarSeekBar(reparabilidadSB, presenter.getReparabilidad());
        configurarSeekBar(reciclabilidadSB, presenter.getReciclabilidad());
        configurarSeekBar(efClimaticaSB, presenter.getEfClimatica());
        configurarSeekBar(efRecursosSB, presenter.getEfRecursos());

        return layout;
    }

    private void configurarSeekBar(CustomSeekBar seekBar, int progress) {
        seekBar.setProgress(progress);
        seekBar.setEnabled(false);
    }

    private void setGraph(HorizontalBarChart chart, int score) {
        chart.setDrawBarShadow(false);
        Description description = new Description();
        description.setText("");
        chart.setDescription(description);
        chart.getLegend().setEnabled(false);
        chart.setPinchZoom(false);
        chart.setDrawValueAboveBar(false);
        chart.setTouchEnabled(false);
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(false);

        YAxis yLeft = chart.getAxisLeft();
        yLeft.setAxisMaximum(100f);
        yLeft.setAxisMinimum(0f);
        yLeft.setEnabled(false);

        YAxis yRight = chart.getAxisRight();
        yRight.setEnabled(false);

        setGraphData(chart, score);
    }

    private void setGraphData(HorizontalBarChart chart, int score) {
        ArrayList<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(0f, score));

        BarDataSet barDataSet = new BarDataSet(entries, "Bar Data Set");
        barDataSet.setColor(ContextCompat.getColor(getContext(), R.color.black));
        barDataSet.setDrawValues(false);

        chart.setDrawBarShadow(true);
        barDataSet.setBarShadowColor(Color.argb(40, 150, 150, 150));
        BarData data = new BarData(barDataSet);

        data.setBarWidth(1f);

        chart.setData(data);
        chart.invalidate();
    }
}

