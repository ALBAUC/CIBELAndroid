package es.unican.appriegospersonales.activities.riesgos.tabs;

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

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.common.adapters.RVAmenazasAdapter;
import es.unican.appriesgospersonales.R;

public class TabAmenazasAppsView extends Fragment implements ITabAmenazasAppsContract.View {
    private ITabAmenazasAppsContract.Presenter presenter;
    private RecyclerView amenazasAppsRV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TabAmenazasAppsPresenter(this);
        presenter.init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_riesgos_apps, container, false);
        amenazasAppsRV = layout.findViewById(R.id.riesgosApps_rv);

        amenazasAppsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        amenazasAppsRV.setAdapter(new RVAmenazasAdapter(getContext(), presenter.getAmenazasApps(), presenter.getPerfilControls()));

        DividerItemDecoration dividerC = new DividerItemDecoration(
                amenazasAppsRV.getContext(),
                DividerItemDecoration.VERTICAL);
        amenazasAppsRV.addItemDecoration(dividerC);
        return layout;
    }

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) requireActivity().getApplication();
    }
}
