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
import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.common.adapters.RVControlesAdapter;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriesgospersonales.R;

public class AssetDetailView extends Fragment implements IAssetDetailContract.View, MainView.RefreshableFragment {

    public static final String FRAGMENT_APP = "aplicacion";
    private IAssetDetailContract.Presenter presenter;
    private Button appAdd_bt;

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
        View layout = inflater.inflate(R.layout.fragment_element_detail, container, false);

        Bundle args = getArguments();
        if (args != null) {
            // Obtener el activo del fragmento que lanzo este fragmento
            Activo activo = args.getParcelable(FRAGMENT_APP);
            presenter = new AssetDetailPresenter(activo, this);
            presenter.init();

            // Link a los elementos de la vista
            ImageView appIcon_iv = layout.findViewById(R.id.appDetailIcon_iv);
            TextView appName_tv = layout.findViewById(R.id.appDetailName_tv);
            TextView appCategory_tv = layout.findViewById(R.id.appDetailCategory_tv);
            RecyclerView appRiesgos_rv = layout.findViewById(R.id.appDetail_riesgos_rv);
            appAdd_bt = layout.findViewById(R.id.appAdd_bt);

            // Asignar valores
            Picasso.get().load(presenter.getAssetIcon()).into(appIcon_iv);
            appName_tv.setText(presenter.getAssetName());
            appCategory_tv.setText(presenter.getAssetCategory());

            appRiesgos_rv.setLayoutManager(new LinearLayoutManager(getContext()));
            appRiesgos_rv.setAdapter(new RVControlesAdapter(getContext(), presenter.getAssetControls(), presenter.getPerfilControls()));

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(appRiesgos_rv.getContext(),
                    DividerItemDecoration.VERTICAL);
            appRiesgos_rv.addItemDecoration(dividerItemDecoration);

            updateDElementAddButton(presenter.isAssetAdded());
            appAdd_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.onAddAssetClicked();
                    updateDElementAddButton(presenter.isAssetAdded());
//                    TabRisksView tabRisksView = (TabRisksView) requireActivity().getSupportFragmentManager().findFragmentById(R.id.viewpager);
//                    if (tabRisksView != null) {
//                        tabRisksView.updateAppList(presenter.getPerfilDElements());
//                    }
                }
            });
        }

        return layout;
    }

    private void updateDElementAddButton(boolean isAppAdded) {
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
    public void refreshData() {
        // No hay nada que actualizar
    }
}
