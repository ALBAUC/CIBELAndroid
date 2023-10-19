package es.unican.appriegospersonales.activities.apps.detail;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import es.unican.appriegospersonales.activities.main.MainView;
import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriesgospersonales.R;

public class AssetDetailView extends Fragment implements IAssetDetailContract.View, MainView.RefreshableFragment {

    public static final String FRAGMENT_APP = "aplicacion";
    private IAssetDetailContract.Presenter presenter;
    private Button appAdd_bt;
    private PieChart cvePC;

    public static AssetDetailView newInstance(Activo activo) {
        AssetDetailView fragment = new AssetDetailView();
        Bundle args = new Bundle();
        args.putParcelable(FRAGMENT_APP, activo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_asset_detail, container, false);

        Bundle args = getArguments();
        if (args != null) {
            // Obtener el activo del fragmento que lanzo este fragmento
            Activo activo = args.getParcelable(FRAGMENT_APP);
            presenter = new AssetDetailPresenter(activo, this);
            presenter.init();

            // Link a los elementos de la vista
            ImageView assetIcon_iv = layout.findViewById(R.id.appDetailIcon_iv);
            TextView assetName_tv = layout.findViewById(R.id.appDetailName_tv);
            TextView assetType_tv = layout.findViewById(R.id.appDetailCategory_tv);
            RecyclerView assetCves_rv = layout.findViewById(R.id.assetDetail_cves_rv);
            appAdd_bt = layout.findViewById(R.id.appAdd_bt);
            cvePC = layout.findViewById(R.id.cve_pc);

            // Asignar valores
            Picasso.get().load(presenter.getAssetIcon())
                    .resize(600, 600)
                    .centerCrop()
                    .into(assetIcon_iv);
            assetName_tv.setText(presenter.getAssetName());
            assetType_tv.setText(presenter.getAssetType());

            assetCves_rv.setLayoutManager(new LinearLayoutManager(getContext()));
            assetCves_rv.setAdapter(new RVCvesAdapter(getContext(), presenter.getAssetCvesOrdenadorPorFecha()));

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(assetCves_rv.getContext(),
                    DividerItemDecoration.VERTICAL);
            assetCves_rv.addItemDecoration(dividerItemDecoration);

            updateAssetAddButton(presenter.isAssetAdded());
            appAdd_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.onAddAssetClicked();
                    updateAssetAddButton(presenter.isAssetAdded());
//                    TabRisksView tabRisksView = (TabRisksView) requireActivity().getSupportFragmentManager().findFragmentById(R.id.viewpager);
//                    if (tabRisksView != null) {
//                        tabRisksView.updateAppList(presenter.getPerfilDElements());
//                    }
                }
            });
        }

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

    private void updateAssetAddButton(boolean isAppAdded) {
        if (isAppAdded) {
            if (isDarkModeEnabled()) {
                appAdd_bt.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.dark_background)));
            } else {
                appAdd_bt.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.light_background)));
            }

            appAdd_bt.setTextColor(ContextCompat.getColor(getContext(), R.color.primary));
            appAdd_bt.setText(R.string.dElement_detail_remove);
        } else {
            if (isDarkModeEnabled()) {
                appAdd_bt.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            } else {
                appAdd_bt.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            }
            appAdd_bt.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.primary)));
            appAdd_bt.setText(R.string.dElement_detail_add);
        }
    }

    private boolean isDarkModeEnabled() {
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                requireActivity().getSupportFragmentManager().popBackStack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) requireActivity().getApplication();
    }

    @Override
    public String getStringFromRes(@StringRes int resourceId) {
        return getContext().getString(resourceId);
    }

    @Override
    public void refreshData() {
        // No hay nada que actualizar
    }
}
