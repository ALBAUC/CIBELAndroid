package es.unican.appriegospersonales.activities.apps.detail.tabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
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

    private static final String PREF_CVES_SORT_ORDER = "cves_sort_order";
    private TextView sortInfoTV;
    private View overlayView;
    private RecyclerView assetCvesRV;
    private SharedPreferences prefs;
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

        // Link a los elementos de la vista
        assetCvesRV = layout.findViewById(R.id.assetDetail_cves_rv);
        cvePC = layout.findViewById(R.id.cve_pc);

        LinearLayout sortLL = layout.findViewById(R.id.sort_ll);
        sortInfoTV = layout.findViewById(R.id.sortInfo_tv);

        // Configurar lista de cves
        assetCvesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(assetCvesRV.getContext(),
                DividerItemDecoration.VERTICAL);
        assetCvesRV.addItemDecoration(dividerItemDecoration);

        // Ordenar cves
        prefs = requireContext().getSharedPreferences("SortCvesPrefs", Context.MODE_PRIVATE);
        String sortOrder = prefs.getString(PREF_CVES_SORT_ORDER, getResources().getString(R.string.ordenarFecha_Rec));
        sortInfoTV.setText(sortOrder);
        updateRecyclerView(sortOrder);

        sortLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSortingOptionsPopup();
            }
        });

        // Configurar grafico de cves
        setUpPieChart();

        return layout;

    }

    private void updateRecyclerView(String sortOrder) {
        if (sortOrder.equals(getResources().getString(R.string.ordenarFecha_Ant))) {
            assetCvesRV.setAdapter(new RVCvesAdapter(getContext(), presenter.getAssetCvesOrdenadorPorFechaAnt()));
        } else if (sortOrder.equals(getResources().getString(R.string.ordenarGravedad_Asc))) {
            assetCvesRV.setAdapter(new RVCvesAdapter(getContext(), presenter.getAssetCvesOrdenadorPorGravedadAsc()));
        } else if (sortOrder.equals(getResources().getString(R.string.ordenarGravedad_Desc))) {
            assetCvesRV.setAdapter(new RVCvesAdapter(getContext(), presenter.getAssetCvesOrdenadorPorGravedadDesc()));
        } else {
            assetCvesRV.setAdapter(new RVCvesAdapter(getContext(), presenter.getAssetCvesOrdenadorPorFechaRec()));
        }
    }

    private void showSortingOptionsPopup() {
        // Mostrar opciones de ordenacion
        View popupView = LayoutInflater.from(getContext()).inflate(R.layout.sorting_options_cves, null);
        PopupWindow popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT, // Ancho completo
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
        );

        setUpSortingOptionsClickListener(popupView, popupWindow);

        View rootView = getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);

        // Destacar opcion de ordenacion actual
        ImageView fechaRecIV = popupView.findViewById(R.id.fechaRec_iv);
        ImageView fechaAntIV = popupView.findViewById(R.id.fechaAnt_iv);
        ImageView gravedadAscIV = popupView.findViewById(R.id.gravedadAsc_iv);
        ImageView gravedadDescIV = popupView.findViewById(R.id.gravedadDesc_iv);
        TextView fechaRecTV = popupView.findViewById(R.id.fecahRec_tv);
        TextView fechaAntTV = popupView.findViewById(R.id.fechaAnt_tv);
        TextView gravedadAscTV = popupView.findViewById(R.id.gravedadAsc_tv);
        TextView gravedadDescTV = popupView.findViewById(R.id.gravedadDesc_tv);

        if (sortInfoTV.getText().equals(getResources().getString(R.string.ordenarFecha_Rec))) {
            fechaRecIV.setVisibility(View.VISIBLE);
            fechaRecTV.setTypeface(Typeface.DEFAULT_BOLD);
            fechaRecTV.setTextColor(ContextCompat.getColor(getContext(), R.color.primary));
        } else if (sortInfoTV.getText().equals(getResources().getString(R.string.ordenarFecha_Ant))) {
            fechaAntIV.setVisibility(View.VISIBLE);
            fechaAntTV.setTypeface(Typeface.DEFAULT_BOLD);
            fechaAntTV.setTextColor(ContextCompat.getColor(getContext(), R.color.primary));
        } else if (sortInfoTV.getText().equals(getResources().getString(R.string.ordenarGravedad_Asc))) {
            gravedadAscIV.setVisibility(View.VISIBLE);
            gravedadAscTV.setTypeface(Typeface.DEFAULT_BOLD);
            gravedadAscTV.setTextColor(ContextCompat.getColor(getContext(), R.color.primary));
        } else if (sortInfoTV.getText().equals(getResources().getString(R.string.ordenarGravedad_Desc))) {
            gravedadDescIV.setVisibility(View.VISIBLE);
            gravedadDescTV.setTypeface(Typeface.DEFAULT_BOLD);
            gravedadDescTV.setTextColor(ContextCompat.getColor(getContext(), R.color.primary));
        }

        // Difuminar resto de la interfaz
        overlayView = new View(getContext());
        overlayView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.dark_background));
        int overlayAlpha = 150; // Valor entre 0 (transparente) y 255 (opaco)
        overlayView.getBackground().setAlpha(overlayAlpha);
        ViewGroup mainLayout = getActivity().findViewById(android.R.id.content);
        mainLayout.addView(overlayView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (overlayView != null) {
                    mainLayout.removeView(overlayView);
                    overlayView = null;
                }
            }
        });
    }

    private void setUpSortingOptionsClickListener(View popupView, PopupWindow popupWindow) {
        // Configurar OnClickListener para cada opción
        LinearLayout linearLayout = popupView.findViewById(R.id.ordenar_cves_ll);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View childView = linearLayout.getChildAt(i);
            if (childView instanceof LinearLayout) {
                childView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int checkedId = view.getId();
                        sortAssets(checkedId);
                        popupWindow.dismiss();
                    }
                });
            }
        }
    }

    private void sortAssets(int checkedId) {
        // Actualizar la opción de ordenación
        String sortOrder = getSortOrderFromCheckedId(checkedId);
        sortInfoTV.setText(sortOrder);
        prefs.edit().putString(PREF_CVES_SORT_ORDER, sortOrder).apply();

        // Actualizar el RecyclerView según la opción de ordenación
        updateRecyclerView(sortOrder);
    }

    private String getSortOrderFromCheckedId(int checkedId) {
        String sortOrder = getResources().getString(R.string.ordenarFecha_Rec);
        switch (checkedId) {
            case R.id.fechaRec_ll:
                sortOrder = getResources().getString(R.string.ordenarFecha_Rec);
                break;
            case R.id.fechaAnt_ll:
                sortOrder = getResources().getString(R.string.ordenarFecha_Ant);
                break;
            case R.id.gravedadAsc_ll:
                sortOrder = getResources().getString(R.string.ordenarGravedad_Asc);
                break;
            case R.id.gravedadDesc_ll:
                sortOrder = getResources().getString(R.string.ordenarGravedad_Desc);
                break;
        }
        return sortOrder;
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
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(12f);

        cvePC.getDescription().setEnabled(false);
        cvePC.setDrawEntryLabels(false);
        cvePC.setRotationEnabled(false);
        cvePC.setTouchEnabled(false);
        cvePC.setDragDecelerationFrictionCoef(0.95f);
        cvePC.setHoleRadius(60f);

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
