package es.unican.appriegospersonales.activities.smarthome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
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
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.unican.appriegospersonales.common.TramosSeekBar;
import es.unican.appriegospersonales.activities.main.MainView;
import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.common.adapters.RVActivosPerfilAdapter;
import es.unican.appriesgospersonales.R;

public class SmartHomeView extends Fragment implements ISmartHomeContract.View, MainView.RefreshableFragment {

    private static final String PREF_ASSETS_PERFIL_SORT_ORDER = "assets_perfil_sort_order";
    private ISmartHomeContract.Presenter presenter;
    private TextView sortInfoTV;
    private View overlayView;
    private RecyclerView devicesRV;
    private SharedPreferences prefs;

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

        // Link a los elementos de la vista
        devicesRV = layout.findViewById(R.id.devicesPerfil_rv);

        TramosSeekBar ecoRatingBar = layout.findViewById(R.id.eco_sb);
        TextView eco0TV = layout.findViewById(R.id.pobre_tv);
        TextView eco1TV = layout.findViewById(R.id.justo_tv);
        TextView eco2TV = layout.findViewById(R.id.bueno_tv);
        TextView eco3TV = layout.findViewById(R.id.excelente_tv);

        TramosSeekBar securityBar = layout.findViewById(R.id.security_sb);
        TextView seguridad0TV = layout.findViewById(R.id.sInseguro_tv);
        TextView seguridad1TV = layout.findViewById(R.id.sMejorable_tv);
        TextView seguridad2TV = layout.findViewById(R.id.sBueno_tv);
        TextView seguridad3TV = layout.findViewById(R.id.sExcelente_tv);

        LinearLayout sortLL = layout.findViewById(R.id.sort_ll);
        sortInfoTV = layout.findViewById(R.id.sortInfo_tv);

        // Configurar lista de dispositivos
        devicesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                devicesRV.getContext(),
                DividerItemDecoration.VERTICAL);
        devicesRV.addItemDecoration(dividerItemDecoration);

        // Ordenar dispositivos
        prefs = requireContext().getSharedPreferences("SortAssetsPrefs", Context.MODE_PRIVATE);
        String sortOrder = prefs.getString(PREF_ASSETS_PERFIL_SORT_ORDER, getResources().getString(R.string.ordenarSeguridad_Asc));
        sortInfoTV.setText(sortOrder);
        updateRecyclerView(sortOrder);

        sortLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSortingOptionsPopup();
            }
        });

        // Configurar barra de sostenibilidad
        ecoRatingBar.setProgress(presenter.getEcoRatingHome());
        ecoRatingBar.setEnabled(false);
        configurarSeekBar(ecoRatingBar, eco0TV, eco1TV, eco2TV, eco3TV);

        // Configurar barra de seguridad
        securityBar.setProgress(presenter.getSecurityRatingHome());
        securityBar.setEnabled(false);
        configurarSeekBar(securityBar, seguridad0TV, seguridad1TV, seguridad2TV, seguridad3TV);

        return layout;
    }

    private void updateRecyclerView(String sortOrder) {
        if (sortOrder.equals(getResources().getString(R.string.ordenarSeguridad_Desc))) {
            devicesRV.setAdapter(new RVActivosPerfilAdapter(getContext(), presenter.getActivosPerfilOrdenadosPorSeguridadDesc(), getMyApplication()));
        } else if (sortOrder.equals(getResources().getString(R.string.ordenarSost_Asc))) {
            devicesRV.setAdapter(new RVActivosPerfilAdapter(getContext(), presenter.getActivosPerfilOrdenadosPorSostAsc(), getMyApplication()));
        } else if (sortOrder.equals(getResources().getString(R.string.ordenarSost_Desc))) {
            devicesRV.setAdapter(new RVActivosPerfilAdapter(getContext(), presenter.getActivosPerfilOrdenadosPorSostDesc(), getMyApplication()));
        } else {
            devicesRV.setAdapter(new RVActivosPerfilAdapter(getContext(), presenter.getActivosPerfilOrdenadosPorSeguridadAsc(), getMyApplication()));
        }
    }

    private void showSortingOptionsPopup() {
        // Mostrar opciones de ordenacion
        View popupView = LayoutInflater.from(getContext()).inflate(R.layout.sorting_options_assets, null);
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
        ImageView seguridadAscIV = popupView.findViewById(R.id.seguridadAsc_iv);
        ImageView seguridadDescIV = popupView.findViewById(R.id.seguridadDesc_iv);
        ImageView sostAscIV = popupView.findViewById(R.id.sostAsc_iv);
        ImageView sostDescIV = popupView.findViewById(R.id.sostDesc_iv);
        TextView seguridadAscTV = popupView.findViewById(R.id.seguridadAsc_tv);
        TextView seguridadDescTV = popupView.findViewById(R.id.seguridadDesc_tv);
        TextView sostAscTV = popupView.findViewById(R.id.sostAsc_tv);
        TextView sostDescTV = popupView.findViewById(R.id.sostDesc_tv);

        if (sortInfoTV.getText().equals(getResources().getString(R.string.ordenarSeguridad_Asc))) {
            seguridadAscIV.setVisibility(View.VISIBLE);
            seguridadAscTV.setTypeface(Typeface.DEFAULT_BOLD);
            seguridadAscTV.setTextColor(ContextCompat.getColor(getContext(), R.color.primary));
        } else if (sortInfoTV.getText().equals(getResources().getString(R.string.ordenarSeguridad_Desc))) {
            seguridadDescIV.setVisibility(View.VISIBLE);
            seguridadDescTV.setTypeface(Typeface.DEFAULT_BOLD);
            seguridadDescTV.setTextColor(ContextCompat.getColor(getContext(), R.color.primary));
        } else if (sortInfoTV.getText().equals(getResources().getString(R.string.ordenarSost_Asc))) {
            sostAscIV.setVisibility(View.VISIBLE);
            sostAscTV.setTypeface(Typeface.DEFAULT_BOLD);
            sostAscTV.setTextColor(ContextCompat.getColor(getContext(), R.color.primary));
        } else if (sortInfoTV.getText().equals(getResources().getString(R.string.ordenarSost_Desc))) {
            sostDescIV.setVisibility(View.VISIBLE);
            sostDescTV.setTypeface(Typeface.DEFAULT_BOLD);
            sostDescTV.setTextColor(ContextCompat.getColor(getContext(), R.color.primary));
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
        LinearLayout linearLayout = popupView.findViewById(R.id.ordenar_activos_ll);
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
        prefs.edit().putString(PREF_ASSETS_PERFIL_SORT_ORDER, sortOrder).apply();

        // Actualizar el RecyclerView según la opción de ordenación
        updateRecyclerView(sortOrder);
    }

    private String getSortOrderFromCheckedId(int checkedId) {
        String sortOrder = getResources().getString(R.string.ordenarSeguridad_Asc);
        switch (checkedId) {
            case R.id.seguridadAsc_ll:
                sortOrder = getResources().getString(R.string.ordenarSeguridad_Asc);
                break;
            case R.id.seguridadDesc_ll:
                sortOrder = getResources().getString(R.string.ordenarSeguridad_Desc);
                break;
            case R.id.sostAsc_ll:
                sortOrder = getResources().getString(R.string.ordenarSost_Asc);
                break;
            case R.id.sostDesc_ll:
                sortOrder = getResources().getString(R.string.ordenarSost_Desc);
                break;
        }
        return sortOrder;
    }

    private void configurarSeekBar(TramosSeekBar seekBar, TextView tv0, TextView tv1, TextView tv2, TextView tv3) {
        int progress = seekBar.getProgress();

        Drawable thumbDrawable = ContextCompat.getDrawable(getContext(), R.drawable.shape_seek_bar_text_thumb);
        LayerDrawable layerDrawable = (LayerDrawable) thumbDrawable;
        GradientDrawable circleDrawable = (GradientDrawable) layerDrawable.getDrawable(1);

        if (progress < 25) {
            tv0.setTextColor(ContextCompat.getColor(getContext(), R.color.seekBar0));
            tv0.setTypeface(Typeface.DEFAULT_BOLD);
            tv0.setTextSize(13f);
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.seekBar0));
        } else if (progress < 50) {
            tv1.setTextColor(ContextCompat.getColor(getContext(), R.color.seekBar1));
            tv1.setTypeface(Typeface.DEFAULT_BOLD);
            tv1.setTextSize(13f);
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.seekBar1));
        } else if (progress < 75) {
            tv2.setTextColor(ContextCompat.getColor(getContext(), R.color.seekBar2));
            tv2.setTypeface(Typeface.DEFAULT_BOLD);
            tv2.setTextSize(13f);
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.seekBar2));
        } else {
            tv3.setTextColor(ContextCompat.getColor(getContext(), R.color.seekBar3));
            tv3.setTypeface(Typeface.DEFAULT_BOLD);
            tv3.setTextSize(13f);
            circleDrawable.setColor(ContextCompat.getColor(getContext(), R.color.seekBar3));
        }
        seekBar.setThumb(thumbDrawable);
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
