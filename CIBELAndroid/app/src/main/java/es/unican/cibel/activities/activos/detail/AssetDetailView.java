package es.unican.cibel.activities.activos.detail;

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
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.Picasso;

import es.unican.cibel.R;
import es.unican.cibel.activities.main.MainView;
import es.unican.cibel.common.MyApplication;
import es.unican.cibel.model.Activo;

public class AssetDetailView extends Fragment implements IAssetDetailContract.View, MainView.RefreshableFragment {

    public static final String FRAGMENT_APP = "aplicacion";
    private IAssetDetailContract.Presenter presenter;
    private Button appAdd_bt;
    private ViewPager2 viewPager;

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
            appAdd_bt = layout.findViewById(R.id.appAdd_bt);
            TextView assetSecurityTV = layout.findViewById(R.id.security_tv);
            viewPager = layout.findViewById(R.id.assetDetailVP);
            TabLayout tabLayout = layout.findViewById(R.id.assetDetailTL);
            ImageView securityIconIV = layout.findViewById(R.id.securityIcon_iv);
            TextView ecoTV = layout.findViewById(R.id.eco_tv);
            ImageView ecoIV = layout.findViewById(R.id.ecoIcon_iv);
            TextView securityTagTV = layout.findViewById(R.id.securityTag_tv);
            TextView ecoTagTV = layout.findViewById(R.id.ecoTag_tv);

            // Asignar valores
            Picasso.get().load(presenter.getAssetIcon())
                    .resize(600, 600)
                    .centerCrop()
                    .into(assetIcon_iv);
            assetName_tv.setText(presenter.getAssetName());
            assetType_tv.setText(presenter.getAssetType());

            assetSecurityTV.setText(presenter.getSecurityRating() + "/100");
            securityIconIV.setColorFilter(ContextCompat.getColor(getContext(), activo.getColorFromTramo(presenter.getSecurityRating())));
            securityTagTV.setText(activo.getEtiquetaSecurityFromTramo(presenter.getSecurityRating()));

            ecoTV.setText(presenter.getEcoPuntuacion() + "/100");
            ecoIV.setColorFilter(ContextCompat.getColor(getContext(), activo.getColorFromTramo(presenter.getEcoPuntuacion())));
            ecoTagTV.setText(activo.getEtiquetaEcoFromTramo(presenter.getEcoPuntuacion()));

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

            viewPager.setAdapter(new VPAssetDetailAdapter(getChildFragmentManager(), getLifecycle(), activo));

            new TabLayoutMediator(tabLayout, viewPager,
                    (tab, position) -> tab.setText(VPAssetDetailAdapter.Tab.byPosition(position).getTitle())
            ).attach();
        }

        return layout;
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
    public void refreshData() {
        // No hay nada que actualizar
    }
}
