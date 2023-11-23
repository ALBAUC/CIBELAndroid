package es.unican.appriegospersonales.activities.apps.detail.tabs;

import android.graphics.Color;
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
        asignaScore(durabilidadTV, presenter.getDurabilidad(), durabilidadSB);
        asignaScore(reciclabilidadTV, presenter.getReciclabilidad(), reciclabilidadSB);
        asignaScore(reparabilidadTV, presenter.getReparabilidad(), reparabilidadSB);
        asignaScore(efClimaticaTV, presenter.getEfClimatica(), efClimaticaSB);
        asignaScore(efRecursosTV, presenter.getEfRecursos(), efRecursosSB);

        return layout;
    }

    private void asignaScore(TextView scoreTV, int score, CustomSeekBar seekBar) {
        scoreTV.setText(String.valueOf(score));
        seekBar.setProgress(score);
        seekBar.setEnabled(false);

        Drawable thumbDrawable = ContextCompat.getDrawable(getContext(), R.drawable.shape_custom_seek_bar);
        LayerDrawable layerDrawable = (LayerDrawable) thumbDrawable;
        GradientDrawable circleDrawable = (GradientDrawable) layerDrawable.getDrawable(1);
        //circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.transparent));
        //circleDrawable.setStroke(1, ContextCompat.getColor(getContext(), R.color.transparent));

        if (score > 75) {
            scoreTV.setTextColor(ContextCompat.getColor(getContext(), R.color.seekBar3));
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.seekBar3));
        } else if (score > 50) {
            scoreTV.setTextColor(ContextCompat.getColor(getContext(), R.color.seekBar2));
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.seekBar2));
        } else if (score > 25) {
            scoreTV.setTextColor(ContextCompat.getColor(getContext(), R.color.seekBar1));
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.seekBar1));
        } else {
            scoreTV.setTextColor(ContextCompat.getColor(getContext(), R.color.seekBar0));
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.seekBar0));
        }

        seekBar.setThumb(thumbDrawable);
    }
}

