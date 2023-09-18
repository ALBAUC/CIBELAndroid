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
import es.unican.appriegospersonales.common.adapters.RVRiesgosAdapter;
import es.unican.appriesgospersonales.R;

public class TabRiesgosAppsView extends Fragment implements ITabRiesgosAppsContract.View {
    private ITabRiesgosAppsContract.Presenter presenter;
    private RecyclerView riesgosAppsRV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TabRiesgosAppsPresenter(this);
        presenter.init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_riesgos_apps, container, false);
        riesgosAppsRV = layout.findViewById(R.id.riesgosApps_rv);

        riesgosAppsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        riesgosAppsRV.setAdapter(new RVRiesgosAdapter(getContext(), presenter.getRiesgosApps(), presenter.getPerfilControls()));

        DividerItemDecoration dividerC = new DividerItemDecoration(
                riesgosAppsRV.getContext(),
                DividerItemDecoration.VERTICAL);
        riesgosAppsRV.addItemDecoration(dividerC);
        return layout;
    }

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) requireActivity().getApplication();
    }
}
