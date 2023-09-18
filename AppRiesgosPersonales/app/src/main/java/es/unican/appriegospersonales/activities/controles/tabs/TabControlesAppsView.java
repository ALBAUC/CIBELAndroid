package es.unican.appriegospersonales.activities.controles.tabs;

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
import es.unican.appriegospersonales.common.adapters.RVControlesAdapter;
import es.unican.appriesgospersonales.R;

public class TabControlesAppsView extends Fragment implements ITabControlesAppsContract.View {

    ITabControlesAppsContract.Presenter presenter;
    private RecyclerView controlesAppsRV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TabControlesAppsPresenter(this);
        presenter.init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_controles_apps, container, false);
        controlesAppsRV = layout.findViewById(R.id.controlesApps_rv);

        controlesAppsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        controlesAppsRV.setAdapter(new RVControlesAdapter(getContext(), presenter.getControlesApps(), presenter.getPerfilControls()));

        DividerItemDecoration dividerC = new DividerItemDecoration(
                controlesAppsRV.getContext(),
                DividerItemDecoration.VERTICAL);
        controlesAppsRV.addItemDecoration(dividerC);
        return layout;
    }

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) requireActivity().getApplication();
    }
}
