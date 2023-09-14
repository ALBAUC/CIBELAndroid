package es.unican.appriegospersonales.activities.apps.detail;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import es.unican.appriegospersonales.activities.main.MainView;
import es.unican.appriegospersonales.activities.perfil.tabs.TabDElementsView;
import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.common.adapters.RVRiesgosAdapter;
import es.unican.appriegospersonales.model.ElementoDigital;
import es.unican.appriesgospersonales.R;

public class DElementDetailView extends Fragment implements IDElementDetailContract.View, MainView.RefreshableFragment {

    public static final String FRAGMENT_APP = "aplicacion";
    private IDElementDetailContract.Presenter presenter;
    private Button appAdd_bt;

    public static DElementDetailView newInstance(ElementoDigital elementoDigital) {
        DElementDetailView fragment = new DElementDetailView();
        Bundle args = new Bundle();
        args.putParcelable(FRAGMENT_APP, elementoDigital);
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
        View layout = inflater.inflate(R.layout.fragment_app_detail, container, false);

        Bundle args = getArguments();
        if (args != null) {
            // Obtener la elementoDigital del fragmento que lanzo este fragmento
            ElementoDigital elementoDigital = args.getParcelable(FRAGMENT_APP);
            presenter = new DElementDetailPresenter(elementoDigital, this);
            presenter.init();

            // Link a los elementos de la vista
            ImageView appIcon_iv = layout.findViewById(R.id.appDetailIcon_iv);
            TextView appName_tv = layout.findViewById(R.id.appDetailName_tv);
            TextView appCategory_tv = layout.findViewById(R.id.appDetailCategory_tv);
            RecyclerView appRiesgos_rv = layout.findViewById(R.id.appDetail_riesgos_rv);
            appAdd_bt = layout.findViewById(R.id.appAdd_bt);

            // Asignar valores
            Picasso.get().load(presenter.getDElementIcono()).into(appIcon_iv);
            appName_tv.setText(presenter.getDElementName());
            appCategory_tv.setText(presenter.getDElementCategory());

            appRiesgos_rv.setLayoutManager(new LinearLayoutManager(getContext()));
            appRiesgos_rv.setAdapter(new RVRiesgosAdapter(getContext(), presenter.getDElementRisks()));

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(appRiesgos_rv.getContext(),
                    DividerItemDecoration.VERTICAL);
            appRiesgos_rv.addItemDecoration(dividerItemDecoration);

            updateAppAddButton(presenter.isDElementAdded());
            appAdd_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.onAddDElementClicked();
                    updateAppAddButton(presenter.isDElementAdded());
                    TabDElementsView tabDElementsView = (TabDElementsView) requireActivity().getSupportFragmentManager().findFragmentById(R.id.viewpager);
                    if (tabDElementsView != null) {
                        tabDElementsView.updateAppList(presenter.getPerfilDElements());
                    }
                }
            });
        }

        return layout;
    }

    private void updateAppAddButton(boolean isAppAdded) {
        if (isAppAdded) {
            if (isDarkModeEnabled()) {
                appAdd_bt.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.dark_background)));
            } else {
                appAdd_bt.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.light_background)));
            }

            appAdd_bt.setTextColor(ContextCompat.getColor(getContext(), R.color.primary));
            appAdd_bt.setText(R.string.app_detail_remove);
        } else {
            if (isDarkModeEnabled()) {
                appAdd_bt.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            } else {
                appAdd_bt.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            }
            appAdd_bt.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.primary)));
            appAdd_bt.setText(R.string.app_detail_add);
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
    public void refreshData() {
        // No hay nada que actualizar
    }
}
