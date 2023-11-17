package es.unican.appriegospersonales.activities.apps.detail.tabs;

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

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import es.unican.appriegospersonales.common.TextThumbSeekBar;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriesgospersonales.R;

public class TabSostenibilidadView extends Fragment implements ITabSostenibilidadContract.View {

    private TabSostenibilidadPresenter presenter;
    private Activo activo;
    private TextThumbSeekBar ecoRatingBar;
    private TextView pobreTV;
    private TextView justoTV;
    private TextView buenoTV;
    private TextView excelenteTV;

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
        ecoRatingBar = layout.findViewById(R.id.eco_sb);
        pobreTV = layout.findViewById(R.id.pobre_tv);
        justoTV = layout.findViewById(R.id.justo_tv);
        buenoTV = layout.findViewById(R.id.bueno_tv);
        excelenteTV = layout.findViewById(R.id.excelente_tv);

        TextView durabilidadTV = layout.findViewById(R.id.durabilidad_tv);
        HorizontalBarChart durabilidadHBC = layout.findViewById(R.id.durabilidad_hbc);

        TextView reparabilidadTV = layout.findViewById(R.id.reparabilidad_tv);
        HorizontalBarChart reparabilidadHBC = layout.findViewById(R.id.reparabilidad_hbc);

        TextView reciclabilidadTV = layout.findViewById(R.id.reciclabilidad_tv);
        HorizontalBarChart reciclabilidadHBC = layout.findViewById(R.id.reciclabilidad_hbc);

        TextView efClimaticaTV = layout.findViewById(R.id.efClimatica_tv);
        HorizontalBarChart efClimaticaHBC = layout.findViewById(R.id.efClimatica_hbc);

        TextView efRecursosTV = layout.findViewById(R.id.efRecursos_tv);
        HorizontalBarChart efRecursosHBC = layout.findViewById(R.id.efRecursos_hbc);

        // Asignar valores
        durabilidadTV.setText(String.valueOf(presenter.getDurabilidad()));
        reparabilidadTV.setText(String.valueOf(presenter.getReparabilidad()));
        reciclabilidadTV.setText(String.valueOf(presenter.getReciclabilidad()));
        efClimaticaTV.setText(String.valueOf(presenter.getEfClimatica()));
        efRecursosTV.setText(String.valueOf(presenter.getEfRecursos()));

        setGraph(durabilidadHBC, presenter.getDurabilidad());
        setGraph(reparabilidadHBC, presenter.getReparabilidad());
        setGraph(reciclabilidadHBC, presenter.getReciclabilidad());
        setGraph(efClimaticaHBC, presenter.getEfClimatica());
        setGraph(efRecursosHBC, presenter.getEfRecursos());

        ecoRatingBar.setProgress(presenter.getEcoRate());
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
            pobreTV.setTextColor(ContextCompat.getColor(getContext(), R.color.seekBar0));
            pobreTV.setTypeface(Typeface.DEFAULT_BOLD);
            pobreTV.setTextSize(13f);
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.seekBar0));
        } else if (progress < 50) {
            justoTV.setTextColor(ContextCompat.getColor(getContext(), R.color.seekBar1));
            justoTV.setTypeface(Typeface.DEFAULT_BOLD);
            justoTV.setTextSize(13f);
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.seekBar1));
        } else if (progress < 75) {
            buenoTV.setTextColor(ContextCompat.getColor(getContext(), R.color.seekBar2));
            buenoTV.setTypeface(Typeface.DEFAULT_BOLD);
            buenoTV.setTextSize(13f);
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.seekBar2));
        } else {
            excelenteTV.setTextColor(ContextCompat.getColor(getContext(), R.color.seekBar3));
            excelenteTV.setTypeface(Typeface.DEFAULT_BOLD);
            excelenteTV.setTextSize(13f);
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.seekBar3));
        }
        ecoRatingBar.setThumb(thumbDrawable);
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

